import dao.Course;
import dao.JDBC_CourseDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author dmifed
 */
public class AppJDBC {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        JDBC_CourseDAO courseDAO = context.getBean(JDBC_CourseDAO.class);
        Course course = courseDAO.findById(2);
        System.out.println(course);
        System.out.println(courseDAO.findAll());
        System.out.println(courseDAO.findByTitle("base"));

        courseDAO.delete(5);

        Course courseIns = new Course();
        courseIns.setTitle("Net systems");
        courseIns.setLenght(54);
        courseIns.setDescriptions("Net systems description");
        //courseDAO.insert(courseIns);

        System.out.println(courseDAO.findAll());

        Course courseUpdate = courseDAO.findById(4);
        courseUpdate.setTitle(courseUpdate.getTitle() + "UPDATED");
        courseUpdate.setLenght(courseUpdate.getLenght() + 9);
        courseUpdate.setDescriptions(courseUpdate.getDescriptions() + "UPDATED");
        courseDAO.update(courseUpdate);

        System.out.println(courseDAO.findAll());




        System.out.println(courseDAO.findAll());

        context.close();
    }
}
