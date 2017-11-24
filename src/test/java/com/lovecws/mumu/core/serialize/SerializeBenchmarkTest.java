package com.lovecws.mumu.core.serialize;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/22/022.
 */
public class SerializeBenchmarkTest {

    private static final Map<String, Object> map = new HashMap<String, Object>();

    static {
        map.put("id", "1");
        map.put("name", "lovecws");
        map.put("sex", "f");
        map.put("password", "5211314");
        map.put("password1", "5211314");
        map.put("password2", "5211314");
        map.put("password3", "5211314");
        map.put("password4", "5211314");
    }

    /*public void serialize(String serialize, int count) {
        long size = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            switch (serialize) {
                case "java":
                    byte[] bytes = JavaSerializeUtil.serialize(map);
                    size += bytes.length;
                    break;
                case "marshalling":
                    bytes = MarshallingSerializeUtil.serialize(map);
                    size += bytes.length;
                    break;
                case "hessian":
                    bytes = HessianSerializeUtil.serialize(map, HessianSerializeUtil.HessianType.HESSIAN);
                    size += bytes.length;
                    break;
                case "hessian2":
                    bytes = HessianSerializeUtil.serialize(map, HessianSerializeUtil.HessianType.HESSIAN2);
                    size += bytes.length;
                    break;
                case "HessianSerializer":
                    bytes = HessianSerializeUtil.serialize(map, HessianSerializeUtil.HessianType.HessianSerializer);
                    size += bytes.length;
                    break;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(serialize + "\ttime:" + (end - start) + "ms\t size:" + size);
    }*/

    /*@Test
    public void run() {
        serialize("java", 10000000);
        serialize("marshalling", 10000000);
        serialize("hessian", 10000000);
        serialize("hessian2", 10000000);
        serialize("HessianSerializer", 10000000);
    }*/
}
