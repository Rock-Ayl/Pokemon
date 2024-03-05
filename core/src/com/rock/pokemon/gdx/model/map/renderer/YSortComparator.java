package com.rock.pokemon.gdx.model.map.renderer;

import java.util.Comparator;

/**
 * 对实现[YSortable]的各种实体进行排序,Y轴越大排在越前面,越先渲染,图层也就越下
 */
public class YSortComparator implements Comparator<YSortable> {

    @Override
    public int compare(YSortable o1, YSortable o2) {
        if (o1.getWorldY() < o2.getWorldY()) {
            return 1;
        } else if (o1.getWorldY() > o2.getWorldY()) {
            return -1;
        }
        return 0;
    }

}