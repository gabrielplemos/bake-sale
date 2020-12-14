package com.bakesale.app.item;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/items")
public class ItemController {

//    @PostMapping
//    public ResponseEntity<AccountResponseDTO> createAccount(
//            @Valid @RequestBody AccountRequestDTO accountRequestDTO) {
//        Account account = accountMapper.convertToEntity(accountRequestDTO);
//        return ResponseEntity.ok(accountService.save(account));
//    }

}
