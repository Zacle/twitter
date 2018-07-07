package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;
import twitter4j.Status;
import twitter4j.User;
import util.ImageCache;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MapMarkerDisplay extends MapMarkerCircle {
    public static final double defaultMarkerSize = 10.0;
    private BufferedImage image;
    private String imageURL;
    private Color color;
    private User user;
    private Status status;

    public MapMarkerDisplay(Layer layer, String name, Coordinate coord, Color color, Status o){
        super(layer, name, coord, defaultMarkerSize, STYLE.FIXED, getDefaultStyle());
        setColor(Color.BLACK);
        setBackColor(color);
        status = o;
        this.user = o.getUser();
        this.imageURL = this.user.getMiniProfileImageURL();
        this.image = ImageCache.getInstance().getImage(imageURL);
    }

    public void paint(Graphics g, Point position, int radius) {
        int size = radius * 2;
        if (g instanceof Graphics2D && this.getBackColor() != null) {
            Graphics2D g2 = (Graphics2D)g;
            Composite oldComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3));
            g2.setPaint(this.getBackColor());
            g.fillOval(position.x - radius, position.y - radius, size + 7, size + 7);
            g2.setComposite(oldComposite);
        }

        g.setColor(this.getColor());
        g.drawImage(this.image, position.x - radius + 6, position.y - radius + 7, size - 5, size - 5, null);
    }

    public User getUser() {
        return user;
    }

    public Status getStatus() {
        return status;
    }
}
