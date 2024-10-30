package test.myfruitshopowner.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import test.myfruitshopowner.repository.HistoryRepository;
import test.myfruitshopowner.service.HistoryService;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    @Override
    public Mono<String> getLastByProductId(Long productId) {
        return historyRepository.findByProductId(productId).map(history -> {
            if(history==null) {
                return "---";
            }else{
                return history.getNumberOfProduct().toString();
            }
        });
    }
}
