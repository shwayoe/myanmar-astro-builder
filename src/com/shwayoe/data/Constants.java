package com.shwayoe.data;

import java.util.Hashtable;
import java.util.Map;

public class Constants {
	
	public static Map<String,Double> getMyanmarCalConstants(int myanmarYear){
		double EI, WO, NM,EW = 0;
		int i;
		int[][] fme;
		int[] wte;
		
		if(myanmarYear > 1312) {
			EI =  3;
			WO = -0.5;
			NM = 8;
			fme = new int[][]{{1377,1}};
			wte = new int[]{1344, 1345};
		}
		else if (myanmarYear > 1217) {
			EI = 2;
			WO = -1;
			NM = 4;
			fme = new int[][] {{1234, 1}, {1261, -1}};
			wte = new int[]{1263, 1264};
		}
		else if (myanmarYear > 1100) {
			EI = 1.3;
			WO = -0.85;
			NM = -1;
			fme = new int[][]{{1120, 1}, {1126, -1}, {1150, 1}, {1172, -1}, {1207, 1}};
            wte = new int[]{1201, 1202};
		}
		else if (myanmarYear > 798) {
			EI = 1.2;
			WO = -1.1;
			NM = -1;
			fme = new int[][]{
                {813, -1}, {849, -1}, {851, -1}, {854, -1}, {927, -1}, {933, -1},
                {936, -1}, {938, -1}, {949, -1}, {952, -1}, {963, -1}, {968, -1},
                {1039, -1}
			};
			
        	wte = new int[]{};
			
		}
		else {
			EI = 1.1;
			WO = -1.1;
			NM = -1;
			
			fme = new int[][]{
                {205, 1}, {246, 1}, {471, 1}, {572, -1}, {651, 1}, {653, 2},
                {656, 1}, {672, 1}, {729, 1}, {767, -1}
			};
			
			wte = new int[]{};
		}
		
		i = binarySearch2(myanmarYear, fme);
		if(i >= 0) {
			WO += fme[i][i];
		}
		
		i = binarySearch1(myanmarYear, wte);
		if(i >=0) {
			EW = 1;
		}
		
		Map<String,Double> myanmarCalConst = new Hashtable<>();
		myanmarCalConst.put("EI", EI);
		myanmarCalConst.put("WO", WO);
		myanmarCalConst.put("NM", NM);
		myanmarCalConst.put("EW", EW);
		
		return myanmarCalConst;
	}
	
	private static int binarySearch2(int key, int[][] arr) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid][0] == key) {
                return mid;
            } else if (arr[mid][0] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
	
	private static int binarySearch1(int key, int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

}
