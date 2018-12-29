import java.awt.*;
import java.io.*;
import java.util.*;

public class Hangar<T extends ITransport> implements Serializable, Comparable<Hangar<T>>,Iterable<T>, Iterator<T>  {
    HashMap<Integer, T> _places;
    private int _maxCount;
    private int _pictureWidth;
    private int _pictureHeight;
    private int _placeSizeWidth = 210;
    private int _placeSizeHeight = 80;
    private int currentIndex;

    public Hangar(int size, int _pictureWidth, int _pictureHeight) {
        _maxCount = size;
        this._places = new HashMap<Integer, T>();
        this._pictureWidth = _pictureWidth;
        this._pictureHeight = _pictureHeight;
    }

    private boolean checkFreePlace(int index) {
        return !_places.containsKey(index);
    }

    public int add(T transport) throws HangarOverflowException, HangarAlreadyHaveException {
        if (_places.size() == _maxCount) {
            throw new HangarOverflowException();
        }
        int index = _places.size();
        for(int i=0;i<_places.size();i++){
            if(checkFreePlace(i))
                index = i;
            if(_places.containsValue(transport))
                throw new HangarAlreadyHaveException();
        }
        if(index != _places.size()) {
            _places.put(index, transport);
            _places.get(index).SetPosition(10 + index / 5 * _placeSizeWidth + 5, index % 5 * _placeSizeHeight + 15, _pictureWidth, _pictureHeight);
            return index;
        }
        _places.put(index,transport);
        _places.get(index).SetPosition(10 + index / 5 * _placeSizeWidth + 5, index % 5 * _placeSizeHeight + 15, _pictureWidth, _pictureHeight);
        return index-1;
    }

    public T del(int index) throws HangarNotFoundException {
        if (!checkFreePlace(index)) {
            T pl = _places.get(index);
            _places.remove(index);
            return pl;
        }
        throw new HangarNotFoundException(index);
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
    public T getTrasport(int ind) throws HangarNotFoundException {
        if (_places.containsKey(ind)) {
            return _places.get(ind);
        }
        throw new HangarNotFoundException(ind);
    }

    public void setTrasport(int ind, T t) throws HangarOccupiedPlaceException {
        if (checkFreePlace(ind)) {
            _places.put(ind, t);
            _places.get(ind).SetPosition(10 + ind / 5 * _placeSizeWidth + 5, ind % 5 * _placeSizeHeight + 15, _pictureWidth, _pictureHeight);
        }
        throw new HangarOccupiedPlaceException(ind);
    }

    @Override
    public int compareTo(Hangar<T> other) {
        if (this._places.size() > other._places.size()) {
            return -1;
        } else if (this._places.size() < other._places.size()) {
            return 1;
        } else {
            Integer[] thisKeys = this._places.keySet().toArray(new Integer[this._places.size()]);
            Integer[] otherKeys = other._places.keySet().toArray(new Integer[other._places.size()]);
            for (int i = 0; i < this._places.size(); i++) {
                if (this._places.get(thisKeys[i]).getClass().equals(plane.class)
                        && other._places.get(otherKeys[i]).getClass().equals(SportPlane.class)) {
                    return 1;
                }
                if (this._places.get(thisKeys[i]).getClass().equals(SportPlane.class)
                        && other._places.get(otherKeys[i]).getClass().equals(plane.class)) {
                    return -1;
                }
                if (this._places.get(thisKeys[i]).getClass().equals(plane.class)
                        && other._places.get(otherKeys[i]).getClass().equals(plane.class)) {
                    return ((plane) this._places.get(thisKeys[i])).compareTo((plane) other._places.get(otherKeys[i]));
                }
                if (this._places.get(thisKeys[i]).getClass().equals(SportPlane.class)
                        && other._places.get(otherKeys[i]).getClass().equals(SportPlane.class)) {
                    return ((SportPlane) this._places.get(thisKeys[i]))
                            .compareTo((SportPlane) other._places.get(otherKeys[i]));
                }
            }
        }
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        if (currentIndex + 1 >= _places.size()) {
            currentIndex = -1;
            return false;
        }
        currentIndex++;
        return true;
    }

    @Override
    public T next() {
        return (T) _places.get(currentIndex);
    }

    private void reset() {
        currentIndex = -1;
    }
}
