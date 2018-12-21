import java.awt.*;
import java.util.*;

public class Hangar<T extends ITransport> {
    HashMap<Integer, T> _places;
    private int _maxCount;
    private int _pictureWidth;
    private int _pictureHeight;
    private int _placeSizeWidth = 210;
    private int _placeSizeHeight = 80;

    public Hangar(int size, int _pictureWidth, int _pictureHeight) {
        _maxCount = size;
        this._places = new HashMap<Integer, T>();
        this._pictureWidth = _pictureWidth;
        this._pictureHeight = _pictureHeight;
    }

    private boolean checkFreePlace(int index) {
        return !_places.containsKey(index);
    }

    public int add(T transport) {
        if (_places.size() == _maxCount) {
            return -1;
        }
        for (int i = 0; i < _maxCount; i++) {
            if (checkFreePlace(i)) {
                _places.put(i, transport);
                _places.get(i).SetPosition(10 + i / 5 * _placeSizeWidth + 5,
                        i % 5 * _placeSizeHeight + 15, _pictureWidth, _pictureHeight);
                return i;
            }
        }
        return -1;
    }

    public T del(int index) {
        if (!checkFreePlace(index)) {
            T pl = _places.get(index);
            _places.remove(index);
            return pl;
        }
        return null;
    }

    public void Draw(Graphics g) {
        DrawMarking(g);
        for (T pl : _places.values()) {
            pl.DrawPlane(g);
        }
    }

    private  void DrawMarking(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, (_maxCount / 5) * _placeSizeWidth, 480);

        for (int i = 0; i < _maxCount / 5; i++)
        {
            for (int j = 0; j < 6; ++j)
            {
                g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight,
                        i * _placeSizeWidth + 110, j * _placeSizeHeight);
            }
            g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, 400);
        }
    }
}
