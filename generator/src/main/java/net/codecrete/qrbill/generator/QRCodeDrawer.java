package net.codecrete.qrbill.generator;

import java.io.IOException;
import net.codecrete.qrbill.canvas.Canvas;

public interface QRCodeDrawer {

    void draw(Canvas graphics, double offsetX, double offsetY) throws IOException;

}
