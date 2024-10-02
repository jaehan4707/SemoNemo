package com.semonemo.spring_server.domain.coin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nft")
@RequiredArgsConstructor
public class CoinController implements CoinApi{
}
