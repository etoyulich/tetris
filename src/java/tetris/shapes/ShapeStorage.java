package tetris.shapes;

import java.util.ArrayList;
import java.util.List;

public class ShapeStorage {

    private final int glassWidth;
    private final int glassHeight;
    private final List<AbstractShape> shapeList = new ArrayList<>();

    public ShapeStorage(int glassWidth, int glassHeight) {
        this.glassWidth = glassWidth;
        this.glassHeight = glassHeight;
        addShapes();
    }

    public AbstractShape getById(int id) throws CloneNotSupportedException {
        return shapeList.get(id).clone();
    }

    public int getStorageSize(){
        return shapeList.size();
    }

    private void addShapes(){
        shapeList.add(StandardShape.generateI(glassWidth, glassHeight));
        shapeList.add(StandardShape.generateJ(glassWidth, glassHeight));
        shapeList.add(StandardShape.generateL(glassWidth, glassHeight));
        shapeList.add(StandardShape.generateO(glassWidth, glassHeight));
        shapeList.add(StandardShape.generateS(glassWidth, glassHeight));
        shapeList.add(StandardShape.generateZ(glassWidth, glassHeight));
        shapeList.add(StandardShape.generateT(glassWidth, glassHeight));
        shapeList.add(NonstandardShape.generateW(glassWidth, glassHeight));
        shapeList.add(NonstandardShape.generateX(glassWidth, glassHeight));
        shapeList.add(NonstandardShape.generateP(glassWidth, glassHeight));
        shapeList.add(NonstandardShape.generateU(glassWidth, glassHeight));
        shapeList.add(NonstandardShape.generateF(glassWidth, glassHeight));
        shapeList.add(NonstandardShape.generateY(glassWidth, glassHeight));
        shapeList.add(NonstandardShape.generateN(glassWidth, glassHeight));
    }

}
