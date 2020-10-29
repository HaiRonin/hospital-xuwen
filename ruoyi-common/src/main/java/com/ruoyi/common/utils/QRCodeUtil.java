package com.ruoyi.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.ImageToBase64;
import org.apache.commons.codec.binary.Base64;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;


/**
 * 二维码工具类
 *
 * @author yixin
 * @createDate 2019年8月22日
 *
 */
public class QRCodeUtil {

    private static final Logger log = LoggerFactory.getLogger(QRCodeUtil.class);

    static String path = Global.getUploadPath();

    // 创建二维码  content为二维码内容 width,height是二维码大小
    public static String crateB64QRCode(String content, int width, int height) {

        String resultImage = "";
        if (!StringUtils.isEmpty(content)) {
            ServletOutputStream stream = null;
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            @SuppressWarnings("rawtypes")
            HashMap<EncodeHintType, Comparable> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 指定字符编码为“utf-8”
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); // 指定二维码的纠错等级为中级
            hints.put(EncodeHintType.MARGIN, 2); // 设置图片的边距

            try {
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

                BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
                ImageIO.write(bufferedImage, "png", os);
                /**
                 * 原生转码前面没有 data:image/png;base64 这些字段，返回给前端是无法被解析，可以让前端加，也可以在下面加上
                 */
                resultImage = new String("data:image/png;base64," + Base64.encodeBase64String(os.toByteArray()));
                return resultImage;
            } catch (Exception e) {
                log.info("二维码生成异常:" + e.getMessage());
            } finally {
                if (stream != null) {
                    try {
                        stream.flush();
                        stream.close();
                    } catch (IOException e) {
                        log.info("二维码输出流关闭异常:" + e.getMessage());
                    }

                }
            }
        }
        return resultImage;
    }

}

