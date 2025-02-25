package com.rock.pokemon.gdx.tool;

import com.rock.pokemon.gdx.util.FastJsonExtraUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 坐标排序器
 *
 * @Author ayl
 * @Date 2023-09-20
 */
@Getter
@Setter
public class XYSortTools {

    /**
     * 坐标对象
     */
    @Getter
    @Setter
    private static class Location {

        //资源坐标
        private Integer x;
        private Integer y;

    }

    /**
     * 实现
     *
     * @param args
     */
    public static void main(String[] args) {
        //原本对象字符
        String input = "[{\"x\":0,\"y\":0},{\"x\":0,\"y\":2},{\"x\":0,\"y\":4},{\"x\":0,\"y\":6},{\"x\":0,\"y\":8},{\"x\":0,\"y\":10},{\"x\":0,\"y\":12},{\"x\":0,\"y\":14},{\"x\":0,\"y\":16},{\"x\":0,\"y\":18},{\"x\":0,\"y\":20},{\"x\":0,\"y\":22},{\"x\":0,\"y\":24},{\"x\":0,\"y\":26},{\"x\":0,\"y\":28},{\"x\":2,\"y\":0},{\"x\":2,\"y\":2},{\"x\":2,\"y\":4},{\"x\":2,\"y\":6},{\"x\":2,\"y\":8},{\"x\":2,\"y\":10},{\"x\":2,\"y\":12},{\"x\":2,\"y\":14},{\"x\":2,\"y\":16},{\"x\":2,\"y\":18},{\"x\":2,\"y\":20},{\"x\":2,\"y\":22},{\"x\":2,\"y\":24},{\"x\":2,\"y\":26},{\"x\":2,\"y\":28},{\"x\":4,\"y\":0},{\"x\":4,\"y\":2},{\"x\":4,\"y\":4},{\"x\":4,\"y\":6},{\"x\":4,\"y\":8},{\"x\":4,\"y\":10},{\"x\":4,\"y\":12},{\"x\":4,\"y\":14},{\"x\":4,\"y\":16},{\"x\":4,\"y\":18},{\"x\":4,\"y\":20},{\"x\":4,\"y\":22},{\"x\":4,\"y\":24},{\"x\":4,\"y\":26},{\"x\":4,\"y\":28},{\"x\":6,\"y\":0},{\"x\":6,\"y\":2},{\"x\":6,\"y\":4},{\"x\":6,\"y\":6},{\"x\":6,\"y\":8},{\"x\":6,\"y\":10},{\"x\":6,\"y\":12},{\"x\":6,\"y\":14},{\"x\":6,\"y\":16},{\"x\":6,\"y\":18},{\"x\":6,\"y\":20},{\"x\":6,\"y\":22},{\"x\":6,\"y\":24},{\"x\":6,\"y\":26},{\"x\":6,\"y\":28},{\"x\":8,\"y\":0},{\"x\":8,\"y\":2},{\"x\":8,\"y\":4},{\"x\":8,\"y\":6},{\"x\":8,\"y\":8},{\"x\":10,\"y\":0},{\"x\":10,\"y\":2},{\"x\":10,\"y\":4},{\"x\":10,\"y\":6},{\"x\":12,\"y\":0},{\"x\":12,\"y\":2},{\"x\":12,\"y\":4},{\"x\":12,\"y\":6},{\"x\":14,\"y\":0},{\"x\":14,\"y\":2},{\"x\":14,\"y\":4},{\"x\":14,\"y\":6},{\"x\":16,\"y\":0},{\"x\":16,\"y\":2},{\"x\":16,\"y\":4},{\"x\":16,\"y\":6},{\"x\":8,\"y\":24},{\"x\":8,\"y\":26},{\"x\":8,\"y\":28},{\"x\":10,\"y\":26},{\"x\":10,\"y\":28},{\"x\":12,\"y\":26},{\"x\":12,\"y\":28},{\"x\":14,\"y\":26},{\"x\":14,\"y\":28},{\"x\":16,\"y\":26},{\"x\":16,\"y\":28},{\"x\":20,\"y\":26},{\"x\":20,\"y\":28},{\"x\":22,\"y\":26},{\"x\":22,\"y\":28},{\"x\":24,\"y\":26},{\"x\":24,\"y\":28},{\"x\":26,\"y\":26},{\"x\":26,\"y\":28},{\"x\":28,\"y\":26},{\"x\":28,\"y\":28},{\"x\":30,\"y\":26},{\"x\":30,\"y\":28},{\"x\":32,\"y\":26},{\"x\":32,\"y\":28},{\"x\":34,\"y\":26},{\"x\":34,\"y\":28},{\"x\":26,\"y\":24},{\"x\":28,\"y\":24},{\"x\":30,\"y\":24},{\"x\":32,\"y\":24},{\"x\":34,\"y\":24},{\"x\":28,\"y\":22},{\"x\":30,\"y\":22},{\"x\":32,\"y\":22},{\"x\":34,\"y\":22},{\"x\":28,\"y\":20},{\"x\":30,\"y\":20},{\"x\":32,\"y\":20},{\"x\":34,\"y\":20},{\"x\":28,\"y\":18},{\"x\":30,\"y\":18},{\"x\":32,\"y\":18},{\"x\":34,\"y\":18},{\"x\":28,\"y\":16},{\"x\":30,\"y\":16},{\"x\":32,\"y\":16},{\"x\":34,\"y\":16},{\"x\":28,\"y\":14},{\"x\":30,\"y\":14},{\"x\":32,\"y\":14},{\"x\":34,\"y\":14},{\"x\":28,\"y\":12},{\"x\":30,\"y\":12},{\"x\":32,\"y\":12},{\"x\":34,\"y\":12},{\"x\":28,\"y\":10},{\"x\":30,\"y\":10},{\"x\":32,\"y\":10},{\"x\":34,\"y\":10},{\"x\":28,\"y\":8},{\"x\":30,\"y\":8},{\"x\":32,\"y\":8},{\"x\":34,\"y\":8},{\"x\":28,\"y\":6},{\"x\":30,\"y\":6},{\"x\":32,\"y\":6},{\"x\":34,\"y\":6},{\"x\":28,\"y\":4},{\"x\":30,\"y\":4},{\"x\":32,\"y\":4},{\"x\":34,\"y\":4},{\"x\":28,\"y\":2},{\"x\":30,\"y\":2},{\"x\":32,\"y\":2},{\"x\":34,\"y\":2},{\"x\":28,\"y\":0},{\"x\":30,\"y\":0},{\"x\":32,\"y\":0},{\"x\":34,\"y\":0},{\"x\":18,\"y\":6},{\"x\":18,\"y\":4},{\"x\":18,\"y\":2},{\"x\":18,\"y\":0},{\"x\":20,\"y\":6},{\"x\":20,\"y\":4},{\"x\":20,\"y\":2},{\"x\":20,\"y\":0},{\"x\":22,\"y\":6},{\"x\":22,\"y\":4},{\"x\":22,\"y\":2},{\"x\":22,\"y\":0},{\"x\":24,\"y\":6},{\"x\":24,\"y\":4},{\"x\":24,\"y\":2},{\"x\":24,\"y\":0},{\"x\":26,\"y\":6},{\"x\":26,\"y\":4},{\"x\":26,\"y\":2},{\"x\":26,\"y\":0},{\"x\":26,\"y\":8},{\"x\":26,\"y\":10},{\"x\":24,\"y\":8}]";
        //解析String为实体
        List<Location> locationList = FastJsonExtraUtils.deepCloneList(input, Location.class);
        //删除脏数据
        locationList.removeIf(p -> p == null || p.getX() == null || p.getY() == null);
        //排序
        locationList.sort((o1, o2) -> {
            //如果x相同
            if (o1.getX().equals(o2.getX())) {
                //对比y
                return o1.getY() - o2.getY();
            } else {
                //对比x
                return o1.getX() - o2.getX();
            }
        });
        //输出
        System.out.println(FastJsonExtraUtils.toJSONString(locationList));
    }

}
