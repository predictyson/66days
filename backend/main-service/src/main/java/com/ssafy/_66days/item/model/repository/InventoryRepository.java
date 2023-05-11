package com.ssafy._66days.item.model.repository;

import com.ssafy._66days.article.model.entity.Article;
import com.ssafy._66days.item.model.entity.Inventory;
import com.ssafy._66days.item.model.entity.InventoryId;
import com.ssafy._66days.item.model.entity.Item;
import com.ssafy._66days.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, InventoryId>{
    Optional<Inventory> findByUserAndItem(User user, Item item);
}
