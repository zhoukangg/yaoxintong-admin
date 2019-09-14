package cn.edu.bupt.yaoxintong.admin.util;

import java.util.List;

public class ListUtil {
	public static final void add2List(List A, List B) {
		if (A != null && B != null) {
			A.removeAll(B);
			A.addAll(B);
		}

	}
}
