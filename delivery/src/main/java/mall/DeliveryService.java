package mall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public void patchDelivery(DeliveryDTO deliveryDTO) {
            Optional<Delivery> deliveryOptional = deliveryRepository.findById(deliveryDTO.getId());
            Delivery delivery = deliveryOptional.get();
            delivery.setStatus(deliveryDTO.getStatus());

            deliveryRepository.save(delivery);
        }

}
