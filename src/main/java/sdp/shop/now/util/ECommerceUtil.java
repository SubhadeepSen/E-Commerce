package sdp.shop.now.util;

public final class ECommerceUtil {

	private final static String EMPTY = "";

	private ECommerceUtil() {
	}

	public static boolean isNotEmpty(String value) {
		if (null != value && !EMPTY.equals(value))
			return true;
		return false;
	}
	
	public static boolean isEmpty(String value) {
		if (null == value || EMPTY.equals(value))
			return true;
		return false;
	}

}
