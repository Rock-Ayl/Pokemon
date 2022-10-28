package com.rock.pokemon.gdx.screen.renderer;

import com.rock.pokemon.gdx.model.YSortable;

import java.util.Comparator;

/**
 * 对Y轴进行排序
 */
public class WorldObjectYComparator implements Comparator<YSortable> {

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
