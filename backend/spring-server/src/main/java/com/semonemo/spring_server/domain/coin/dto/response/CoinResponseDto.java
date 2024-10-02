package com.semonemo.spring_server.domain.coin.dto.response;

import com.semonemo.spring_server.domain.blockchain.dto.NFTInfoDto;

import java.math.BigInteger;
import java.util.List;

public record CoinResponseDto(
    Long userId,
    Long current
) {
}
