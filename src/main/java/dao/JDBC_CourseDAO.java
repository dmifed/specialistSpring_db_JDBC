package dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dmifed
 */
public class JDBC_CourseDAO implements CourseDAO {

    private static final String SELECT_ALL = "select id, title, lenght, descriptions from courses";
    private static final String SELECT_BY_ID = SELECT_ALL + " where id = ?";
    private static final String SELECT_BY_TITLE = SELECT_ALL + " where title like '%' ? '%' ";
    private static final String INSERT = "insert into courses value (?, ?, ?, ?)";
    private static final String UPDATE = "update courses set title = ?, lenght = ?, descriptions = ? where id = ?";
    private static final String DELETE = "delete from courses where id = ?";


    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Course findById(int id) {
        final Course course = new Course();
        jdbcTemplate.queryForObject(SELECT_BY_ID, new Object[]{id}, new RowMapper<Object>() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

                course.setId(rs.getInt("id"));
                course.setTitle(rs.getString("title"));
                course.setLenght(rs.getInt("lenght"));
                course.setDescriptions(rs.getString("descriptions"));
                return course;

            }
        });
        return course;
    }

    public List<Course> findAll() {
/*        List<Course> courses = new ArrayList<>();
        //String -- row
        //Object -- value in row
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(SELECT_ALL);
        for(Map<String, Object> row : rows){
            Course course = new Course();
            course.setId((int)row.get("id"));
            course.setLenght((int)row.get("lenght"));
            course.setTitle((String) row.get("title"));
            course.setDescriptions((String) row.get("descriptions"));
            courses.add(course);
        }
        return courses;*/

        List<Course> courses = getJdbcTemplate().query(SELECT_ALL, new BeanPropertyRowMapper<>(Course.class));
        return courses;
    }

    public List<Course> findByTitle(String title) {
/*        List<Course> courses =
        getJdbcTemplate().query(SELECT_BY_TITLE, new Object[]{title}, new RowMapper<Course>() {
            @Override
            public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setTitle(rs.getString("title"));
                course.setLenght(rs.getInt("lenght"));
                course.setDescriptions(rs.getString("descriptions"));
                return course;
            }
        });
        return courses;*/

        List<Course> courses =
                getJdbcTemplate().query(SELECT_BY_TITLE, new Object[]{title}, new BeanPropertyRowMapper<>(Course.class));
        return courses;

    }

    public void insert(Course course) {
        int courseId = getJdbcTemplate().getFetchSize() + 1;
        getJdbcTemplate().update(INSERT, courseId, course.getTitle(), course.getLenght(), course.getDescriptions());
    }

    public void update(Course course) {
        int id = course.getId();
        getJdbcTemplate().update(UPDATE, course.getTitle(), course.getLenght(), course.getDescriptions(), id);
    }

    public void delete(int id) {
        getJdbcTemplate().update(DELETE, id);
    }
}
