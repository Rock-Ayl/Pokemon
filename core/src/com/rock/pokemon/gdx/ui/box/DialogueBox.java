package com.rock.pokemon.gdx.ui.box;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.rock.pokemon.gdx.common.Settings;

/**
 * 对话框实体
 */
public class DialogueBox extends Table {

    /**
     * 动画元素
     */

    //当前动画目标总文字
    private String targetText = "";

    //当前已执行动画时长
    private float animTimer = 0F;

    //对话框当前动画总时长(会按照字数计算)
    private float animationTotalTime = 0F;

    //一个char的动画时长
    private static final float TIME_PER_CHARACTER = 0.05F;

    //当前对话框状态,默认空闲
    private STATE state = STATE.IDLE;

    //对话框状态枚举
    private enum STATE {

        //动画中
        ANIMATING,
        //空闲
        IDLE,

        ;

    }

    /**
     * 基础
     */

    //文本
    private Label textLabel;

    /**
     * 根据皮肤初始化对话框
     *
     * @param skin
     */
    public DialogueBox(Skin skin) {
        //初始化父级
        super(skin);
        //使用对话框本身的背景图片
        this.setBackground(Settings.UI_IMAGE_DIALOGUE_BOX);
        //初始化[label],并从皮肤中获取[对话框][样式]的皮肤,并使用
        this.textLabel = new Label("\n", skin, Settings.STYLE_DIALOGUE_BOX_LABEL);
        //将文本组装至文本框中
        this.add(this.textLabel)
                //均匀分布该label
                .expand()
                //左对齐
                .align(Align.left)
                //和周围间距
                .pad(Settings.SCALE * 3);
    }

    /**
     * 设置文本(动态)
     *
     * @param text 文本内容
     */
    public void animateText(String text) {
        //记录目标文本
        this.targetText = text;
        //计算并初始化本次动画总时长
        this.animationTotalTime = text.length() * TIME_PER_CHARACTER;
        //更改对话框状态
        this.state = STATE.ANIMATING;
        //初始化当前动画时长
        this.animTimer = 0F;
    }

    /**
     * 设置文本(非动态)
     *
     * @param text 文本内容
     */
    private void setText(String text) {
        //判断一个文本是否包含换行,如果不包含
        if (text.contains("\n") == false) {
            //我们要给它加一个换行(这样会始终保持最少两行,让我们显示保持2行,模仿绿宝石)
            text += "\n";
        }
        //设置文本
        this.textLabel.setText(text);
    }

    /**
     * 处理对话框帧动画
     *
     * @param delta
     */
    @Override
    public void act(float delta) {
        //根据状态处理
        switch (state) {
            //动画中
            case ANIMATING:
                //叠加动画总市场
                animTimer += delta;
                //如果动画时长过了总时间了
                if (animTimer >= animationTotalTime) {
                    //更改状态
                    state = STATE.IDLE;
                    //变更为总时间
                    animTimer = animationTotalTime;
                }
                //计算出当前动画时间该播放的文本数量/坐标(转化为整数)
                int charactersToDisplay = (int) ((animTimer / animationTotalTime) * targetText.length());
                //从总文本中切割出实际对话文本
                String actuallyDisplayedText = targetText.substring(0, charactersToDisplay);
                //设置实际对话文本
                setText(actuallyDisplayedText);
                //结束
                break;
            //空闲/其他
            case IDLE:
            default:
                //结束
                break;
        }
    }

    /**
     * 判断是否已完成
     *
     * @return
     */
    public boolean isFinished() {
        if (state == STATE.IDLE) {
            return true;
        } else {
            return false;
        }
    }

}
