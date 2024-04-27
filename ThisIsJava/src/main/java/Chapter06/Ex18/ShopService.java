package Chapter06.Ex18;

public class ShopService {
    private static final ShopService shopService = new ShopService();

    private ShopService() {

    }

    public static ShopService getInstance() {
        return shopService;
    }
}
