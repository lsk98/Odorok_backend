package com.odorok.OdorokApplication.diary.service;

import com.odorok.OdorokApplication.diary.dto.response.DiaryDetail;
import com.odorok.OdorokApplication.diary.dto.response.DiaryPermissionCheckResponse;
import com.odorok.OdorokApplication.diary.repository.DiaryRepository;
import com.odorok.OdorokApplication.draftDomain.Inventory;
import com.odorok.OdorokApplication.draftDomain.Item;
import com.odorok.OdorokApplication.repository.InventoryRepository;
import com.odorok.OdorokApplication.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class DiaryServiceImpl implements DiaryService{
    private final DiaryRepository diaryRepository;
    private final InventoryRepository inventoryRepository;
    private final ItemRepository itemRepository;

    private Long diaryPermissionItemId;

    // 일지 생성권 itemId 캐싱.
    @PostConstruct
    public void initDiaryItemId() {
        this.diaryPermissionItemId = itemRepository.findByName("일지 생성권")
                .map(Item::getId)
                .orElseThrow(() ->
                        new IllegalStateException("'일지 생성권' 아이템이 DB에 없습니다. 서버 실행 중단. 데이터 삽입을 확인하세요.")
                );
    }

    @Override
    public DiaryDetail findDiaryById(long userId, long diaryId){
        return diaryRepository.findDiaryById(userId, diaryId);
    }

    @Override
    public DiaryPermissionCheckResponse findDiaryPermission(long userId) {
        Inventory inventory = inventoryRepository.findByUserIdAndItemId(userId, diaryPermissionItemId)
                .orElseGet(() -> Inventory.builder().userId(userId).itemId(diaryPermissionItemId).count(0).build());
        return new DiaryPermissionCheckResponse(inventory.getUserId(), inventory.getItemId(), inventory.getCount());
    }
}
