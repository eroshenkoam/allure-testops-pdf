package io.github.eroshenkoam.allure.utils;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.io.FileOutputStream;
import java.io.OutputStream;

public final class PDFUtils {

    private PDFUtils() {
    }

    public static void saveToFile(final String html, final String filePath) throws Exception {
        try (OutputStream os = new FileOutputStream(filePath)) {
            final PdfRendererBuilder builder = new PdfRendererBuilder()
                    .withHtmlContent(html, null)
                    .toStream(os)
                    .useFastMode();
            builder.run();
        }
    }

}
