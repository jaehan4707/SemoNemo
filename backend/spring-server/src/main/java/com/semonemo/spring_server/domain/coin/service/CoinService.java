package com.semonemo.spring_server.domain.coin.service;

import com.semonemo.spring_server.domain.coin.dto.request.CoinRequestDto;
import com.semonemo.spring_server.domain.coin.dto.request.CoinServiceRequestDto;
import com.semonemo.spring_server.domain.coin.dto.response.CoinHistoryDto;
import com.semonemo.spring_server.domain.coin.dto.response.CoinResponseDto;
import com.semonemo.spring_server.domain.coin.dto.response.TradeLogResponseDto;
import com.semonemo.spring_server.domain.coin.entity.CoinPrice;

import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.List;

public interface CoinService {
    CoinResponseDto mintCoin(CoinServiceRequestDto coinRequestDto);

    CoinResponseDto getCoin(Long userId);

    Page<TradeLogResponseDto> getTradeLog(Long userId, int page, int size);

    Long payableToCoin(Long userId, Long amount, BigInteger tradeId);

    Long coinToPayable(Long userId, Long amount, BigInteger tradeId);

    CoinPrice getCoinPrice();

    List<CoinHistoryDto> getWeeklyPrices();
}
