package cs.ds.xml;

import com.thoughtworks.xstream.XStream;
import cs.ds.blogdomain.Author;
import cs.ds.blogdomain.Blog;
import cs.ds.blogdomain.Entry;

/**
 * Created by dell on 11/28/14.
 */
public class BlogExample1 {
    public static void main(String[] args) {
        Blog teamBlog = new Blog(new Author("Guilherme Silveira"));
        teamBlog.add(new Entry("first", "My first blog entry."));
        teamBlog.add(new Entry("tutorial",
                "Today we have developed a nice alias tutorial. Tell your friends! NOW!"));

        XStream xstream = new XStream();
        xstream.alias("blog", Blog.class);
        xstream.alias("entry", Entry.class);

        System.out.println(xstream.toXML(teamBlog));
    }
}
