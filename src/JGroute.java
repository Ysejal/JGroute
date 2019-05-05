import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class JGroute {
    @Test
    public void givenAdaptedGraph_whenWriteBufferedImage_thenFileShouldExist() throws IOException {
        DefaultUndirectedGraph<String, DefaultEdge> g = new DefaultUndirectedGraph<>(DefaultEdge.class);

        String t1 = "Tier 1";
        String t2 = "Tier 2";
        String t3 = "Tier 3";

        g.addVertex(t1);
        g.addVertex(t2);
        g.addVertex(t3);

        g.addEdge(t1, t2);
        g.addEdge(t2, t3);
        g.addEdge(t3, t1);

        JGraphXAdapter<String, DefaultEdge> graphAdapter =
                new JGraphXAdapter<String, DefaultEdge>(g);
        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        BufferedImage image =
                mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);
        File imgFile = new File("graph.png");
        ImageIO.write(image, "PNG", imgFile);

        assertTrue(imgFile.exists());
    }
}

