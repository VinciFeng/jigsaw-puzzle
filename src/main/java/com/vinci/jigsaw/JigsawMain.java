package com.vinci.jigsaw;

import com.vinci.jigsaw.component.JigsawPuzzle;
import com.vinci.jigsaw.constant.JigsawConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vinci
 * @date 2022/07/14
 */
public class JigsawMain {

    private static final Logger logger = LoggerFactory.getLogger(JigsawMain.class);

    public static void main(String[] args) {
        logger.info("Welcome to Jigsaw!");
        JigsawPuzzle jigsawPuzzle = new JigsawPuzzle();
        jigsawPuzzle.showInitBoard();
        long start = System.currentTimeMillis();
        jigsawPuzzle.searchResult();
        logger.info(JigsawConstant.THE_USED_TIME, (System.currentTimeMillis() - start));
        jigsawPuzzle.showResult();
    }
}
