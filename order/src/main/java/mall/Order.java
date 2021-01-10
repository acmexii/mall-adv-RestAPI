package mall;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String productId;
    private Integer qty;
    private String status;

    @PostPersist
    public void onPostPersist(){
        Ordered ordered = new Ordered();
        BeanUtils.copyProperties(this, ordered);
        ordered.publishAfterCommit();
    }

    @PrePersist
     public void onPrePersist(){
      try {
          Thread.currentThread().sleep((long) (800 + Math.random() * 220));
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
    }

    @PostUpdate
    public void onPostUpdate(){
        System.out.println("################# Oder Status Updated and Update Event raised..!!");
    }

    @PreRemove
    public void onPreRemove(){
        OrderCancelled orderCancelled = new OrderCancelled();
        BeanUtils.copyProperties(this, orderCancelled);
        orderCancelled.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        /**** CREATE ****/
        mall.external.Cancellation cancellation = new mall.external.Cancellation();
        cancellation.setOrderId(this.getId());
        cancellation.setStatus("Delivery Cancelled");
        OrderApplication.applicationContext.getBean(mall.external.CancellationService.class).cancelShip(cancellation);

//        /**** READ ****/
//        List<String> result;
//        ArrayList<String> param = new ArrayList<String>();
//        param.add(new String("productId"));
//        result = OrderApplication.applicationContext.getBean(mall.external.CancellationService.class).array(param);
//        System.out.println(result);

        /**** UPDATE ****/
//        mall.external.Delivery delivery = new mall.external.Delivery();
//        delivery.setOrderId(this.getId());
//        delivery.setStatus("Delivery Cancelled");
//        String _putResult = OrderApplication.applicationContext.getBean(mall.external.CancellationService.class)
//                .put_by_rest_api(this.getId(), delivery);
//        System.out.println(_putResult);

//        /**** UPDATE ****/
//        mall.external.Delivery delivery = new mall.external.Delivery();
//        delivery.setId(this.getId());
//        delivery.setStatus("Delivery Cancelled");
//        String _patchResult = OrderApplication.applicationContext.getBean(mall.external.CancellationService.class)
//                .patch_by_rest_api(delivery);
//        System.out.println(_patchResult);

        /**** UPDATE ****/
//        mall.external.Delivery delivery = new mall.external.Delivery();
//        delivery.setId(this.getId());
//        delivery.setStatus("Delivery Cancelled");
//        String _patchResult = OrderApplication.applicationContext.getBean(mall.external.CancellationService.class)
//                .patch_by_Controller(delivery);
//        System.out.println(_patchResult);

        /**** DELETE ****/
//        Long targetId = this.getId();
//        String _deleteResult = OrderApplication.applicationContext.getBean(mall.external.CancellationService.class)
//                .delete(targetId);
//        System.out.println(_deleteResult);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
