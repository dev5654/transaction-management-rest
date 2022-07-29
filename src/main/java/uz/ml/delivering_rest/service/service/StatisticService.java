package uz.ml.delivering_rest.service.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.ml.delivering_rest.dto.region.RegionGetDTO;
import uz.ml.delivering_rest.dto.statistisc.StatisticForCarrierScore;
import uz.ml.delivering_rest.dto.statistisc.StatisticForProduct;
import uz.ml.delivering_rest.dto.statistisc.StatisticForRegion;
import uz.ml.delivering_rest.entity.entity.Carrier;
import uz.ml.delivering_rest.entity.entity.Product;
import uz.ml.delivering_rest.entity.entity.Region;
import uz.ml.delivering_rest.entity.entity.Transactions;
import uz.ml.delivering_rest.mapper.mapper.CarrierMapper;
import uz.ml.delivering_rest.mapper.mapper.RegionMapper;
import uz.ml.delivering_rest.repository.repository.ProductRepository;
import uz.ml.delivering_rest.repository.repository.RegionRepository;
import uz.ml.delivering_rest.repository.repository.TransactionsRepository;
import uz.ml.delivering_rest.response.ApplicationError;
import uz.ml.delivering_rest.response.Data;
import uz.ml.delivering_rest.service.BaseService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticService implements BaseService {

    private final TransactionsRepository transactionsRepository;

    public StatisticService(TransactionsRepository transactionsRepository, RegionRepository regionRepository, ProductRepository productRepository, RegionMapper mapper, CarrierMapper carrierMapper) {
        this.transactionsRepository = transactionsRepository;
        this.regionRepository = regionRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.carrierMapper = carrierMapper;
    }

    private final RegionRepository regionRepository;
    private final ProductRepository productRepository;
    private final RegionMapper mapper;
    private final CarrierMapper carrierMapper;

    public ResponseEntity<?> deliveryRegionsPerNT() {
        List<StatisticForRegion> statisticForRegions = new ArrayList<>();
        Map<Integer, List<RegionGetDTO>> map = new HashMap<>();
        List<Region> regions = regionRepository.findAll();
        for (Region region : regions) {
            if (!map.containsKey(region.getTransactionCount())) {
                map.put(region.getTransactionCount(), new ArrayList<>(List.of(mapper.toGetDTO(region))));
            } else map.forEach((integer, r) -> {
                if (Objects.equals(integer, region.getTransactionCount())) {
                    map.get(integer).add(mapper.toGetDTO(region));
                }
            });
        }

        map.forEach((integer, r) -> {
            StatisticForRegion statisticForRegion = new StatisticForRegion(integer, r);
            statisticForRegions.add(statisticForRegion);
        });
        return new ResponseEntity<>(statisticForRegions.stream().sorted(Comparator.comparing(StatisticForRegion::getTransactionNumber).reversed()).toList(), HttpStatus.OK);
    }

    public ResponseEntity<?> numberOfTranPerProduct() {
        List<StatisticForProduct> statisticForProductList = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.getTransactionCount() > 0) {
                StatisticForProduct statisticForProduct = StatisticForProduct.builder().productId(product.getId()).transactionCount(product.getTransactionCount()).build();
                statisticForProductList.add(statisticForProduct);
            }
        }
        return new ResponseEntity<>(statisticForProductList, HttpStatus.OK);
    }

    public ResponseEntity<?> scorePerCarrier(Integer minimumScore) {
        if (minimumScore < 1 || minimumScore > 10)
            return new ResponseEntity<>(ApplicationError.builder().message("400").build(), HttpStatus.BAD_REQUEST);
        List<Transactions> transactionsList = transactionsRepository.findAll();
        List<StatisticForCarrierScore> carrierStatistics = new ArrayList<>();
        Map<Carrier, Integer> map = new HashMap<>();
        for (Transactions transactions : transactionsList) {
            if (transactions.getScore() > minimumScore) {
                if (map.containsKey(transactions.getCarrier())) {
                    map.put(transactions.getCarrier(), map.get(transactions.getCarrier()) + transactions.getScore());
                } else map.put(transactions.getCarrier(), transactions.getScore());
            }
        }
        map.forEach((carrier, sum) -> {
            StatisticForCarrierScore statisticForTransactions = new StatisticForCarrierScore(carrierMapper.toGetDTO(carrier), sum);
            carrierStatistics.add(statisticForTransactions);
        });

        List<StatisticForCarrierScore> statisticForCarrierScores = carrierStatistics.stream().sorted(Comparator.comparing(o -> o.getCarrier().getName())).collect(Collectors.toList());
        return new ResponseEntity<>(new Data<>(statisticForCarrierScores), HttpStatus.OK);
    }
    // 123
}
