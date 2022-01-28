package jpabook.jpashop.repository;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item) {
        if(item.getId()==null){
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    @Transactional
    public void update(Item item) {
        //Item item = 파라미터로 넘어온 준영속 상태의 엔티티
        Item mergeItem = em.merge(item);

    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
