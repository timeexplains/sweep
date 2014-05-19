package util;

import java.util.Random;

/**
 * 
 * @author zhihuaPeng
 *
 */
public class RandomUtil {
	private static Random  random = new Random();
	
	/**
	 * 
	 * @param num
	 * @return
	 */
	public static int getRandomNum(int num){
		return random.nextInt(num);
	}

}
