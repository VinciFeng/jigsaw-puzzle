package com.vinci.jigsaw.component;

import com.vinci.jigsaw.tool.ArrayTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 拼图碎片类
 * @author Vinci
 * @date 111/01/11
 */
public class JigsawPiece {

    private static final Logger logger = LoggerFactory.getLogger(JigsawPiece.class);

    int[][] baseShape;

    List<int[][]> shapes;

    /** 构造方法默认不翻转 */
    public JigsawPiece(int[][] shape) {
        this(shape, false);
    }

    /** 构造方法自定义是否翻转 */
    public JigsawPiece(int[][] shape, boolean withFlip) {
        this.baseShape = ArrayTool.deepCopyArray(shape);
        this.shapes = new ArrayList<>();
        if (withFlip) {
            rotateAndFlipJigsawPiece(baseShape);
        } else {
            rotateJigsawPiece(baseShape, false);
        }
    }

    /** 根据输入的shape形状，旋转和翻转拼图碎片的各种形状并保存 */
    private void rotateAndFlipJigsawPiece(int[][] shape) {
        // 旋转形状并添加
        rotateJigsawPiece(shape, false);
        // 翻转形状再添加一次
        rotateJigsawPiece(ArrayTool.flip(shape), true);
    }

    /** 根据输入的shape形状，旋转拼图碎片的各种形状并保存 */
    private void rotateJigsawPiece(int[][] shape, boolean isFlip) {
        // 添加原始形状，如果是镜像，需要判断是否重复
        if (!isFlip || !checkRepeat(shape)) {
            shapes.add(shape);
        }
        int[][] next = ArrayTool.deepCopyArray(shape);
        // 旋转并添加
        for (int i = 0; i < 3; i++) {
            next = ArrayTool.rotate(next);
            // 去重剪枝
            if (!checkRepeat(next)) {
                shapes.add(next);
            }
        }
    }

    /** 判断该形状是否已存在 */
    private boolean checkRepeat(int[][] shape) {
        boolean repeat = false;
        for (int[][] sh : shapes) {
            if (ArrayTool.isRepeat(sh, shape)) {
                repeat = true;
                break;
            }
        }
        return repeat;
    }

    public List<int[][]> getShapes() {
        return shapes;
    }
}
