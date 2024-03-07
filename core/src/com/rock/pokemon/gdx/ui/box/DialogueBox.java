package com.rock.pokemon.gdx.ui.box;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.rock.pokemon.gdx.PokemonGame;
import com.rock.pokemon.gdx.common.Settings;

/**
 * 对话框 实体
 */
public class DialogueBox extends Table {

    /**
     * 基础
     */

    //文本标签
    private Label textLabel;

    //对应父级 对话框+可选项实体
    private DialogueAndOptionBox dialogueAndOptionBox;

    /**
     * 动画元素-变动
     */

    //当前动画目标总文字
    private String targetText = "";

    //当前已执行动画时长
    private float animTimer = 0F;

    //对话框当前动画总时长(会按照字数计算)
    private float animationTotalTime = 0F;

    //当前对话框状态,默认空闲
    private STATE state = STATE.IDLE;

    /**
     * 动画元素-固定
     */

    //一个char的动画时长
    private static final float TIME_PER_CHARACTER = 0.05F;

    //对话框状态枚举
    private enum STATE {

        //动画中
        ANIMATING,
        //空闲
        IDLE,

        ;

    }

    /**
     * 初始化对话框
     *
     * @param pokemonGame          游戏对象
     * @param dialogueAndOptionBox 对应 对话框+可选项实体
     */
    public DialogueBox(PokemonGame pokemonGame, DialogueAndOptionBox dialogueAndOptionBox) {

        //初始化父级
        super(pokemonGame.getSkin());

        //记录
        this.dialogueAndOptionBox = dialogueAndOptionBox;

        //设置整体背景贴图
        this.setBackground(Settings.UI_IMAGE_DIALOGUE_BOX);
        //初始化字体,载入字体
        this.textLabel = new Label("\n", pokemonGame.getSkin(), Settings.SYSTEM_FONT_LABEL);
        //将文本组装至文本框中
        this.add(this.textLabel)
                //均匀分布该label
                .expand()
                //左对齐
                .align(Align.left)
                //和左边的间距
                .padLeft(Settings.SCALE_TILE_SIZE / 2);
    }

    /**
     * 设置文本(动态)
     *
     * @param text 文本内容
     */
    public void animateText(String text) {
        //记录目标文本
        this.targetText = text;
        //初始化当前动画时长
        this.animTimer = 0F;
        //计算并初始化本次动画总时长
        this.animationTotalTime = text.length() * TIME_PER_CHARACTER;
        //更改对话框状态
        this.state = STATE.ANIMATING;
    }

    /**
     * 设置文本(非动态)
     *
     * @param text 文本内容
     */
    private void setText(String text) {
        //根据分段拆分句子为数组
        String[] sentenceArr = text.split("\n");
        //如果只有一行
        if (sentenceArr.length == 1) {
            //固定为2行
            String sentence = sentenceArr[sentenceArr.length - 1] + "\n";
            //设置文本
            this.textLabel.setText(sentence);
        } else {
            //永远只需要两行
            String sentence = sentenceArr[sentenceArr.length - 2] + "\n" + sentenceArr[sentenceArr.length - 1];
            //设置文本
            this.textLabel.setText(sentence);
        }
    }

    /**
     * 处理对话框帧动画
     *
     * @param delta
     */
    @Override
    public void act(float delta) {
        //根据状态处理
        switch (this.state) {
            //动画中
            case ANIMATING:

                /**
                 * 自身的处理
                 */

                //叠加动画总市场
                this.animTimer += delta;
                //如果动画时长过了总时间了
                if (this.animTimer >= this.animationTotalTime) {
                    //更改状态
                    this.state = STATE.IDLE;
                    //变更为总时间
                    this.animTimer = this.animationTotalTime;
                }
                //计算出当前动画时间该播放的文本数量/坐标(转化为整数)
                int charactersToDisplay = (int) ((this.animTimer / this.animationTotalTime) * this.targetText.length());
                //从总文本中切割出实际对话文本
                String actuallyDisplayedText = this.targetText.substring(0, charactersToDisplay);
                //设置实际对话文本
                setText(actuallyDisplayedText);

                /**
                 * 完成后的回调
                 */

                //如果动画结束
                if (isFinished()) {
                    //回调父级
                    this.dialogueAndOptionBox.dialogueBoxFinish();
                }

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
        //返回
        return this.state == STATE.IDLE;
    }

}
