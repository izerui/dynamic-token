import com.github.token.DynamicToken;
import com.github.token.IntervalEnum;
import com.github.token.TokenHelper;

public class Tests {

    public static void main(String[] args) {
        while (true) {
            DynamicToken token = new DynamicToken("123456", IntervalEnum.INTERVAL_HALF);
            TokenHelper helper = new TokenHelper();
            // 该动态key作为签名key
            String key = helper.getToken(token);
            System.out.println(key);
        }
    }
}
