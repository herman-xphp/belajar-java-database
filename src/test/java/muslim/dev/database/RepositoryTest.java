package muslim.dev.database;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muslim.dev.database.entity.Comment;
import muslim.dev.database.repository.CommentRepository;
import muslim.dev.database.repository.CommentRepositoryImpl;

public class RepositoryTest {

  private CommentRepository commentRepository;

  @BeforeEach
  void setUp() {
    commentRepository = new CommentRepositoryImpl();
  }

  @Test
  void testInsert() {
    Comment comment = new Comment("admin@gmail.com", "Hello");
    commentRepository.insert(comment);

    Assertions.assertNotNull(comment);
    System.out.println(comment.getId());
  }

  @Test
  void testFindById() {
    Comment comment = commentRepository.findById(2003);
    Assertions.assertNotNull(comment);
    System.out.println(comment.getId());
    System.out.println(comment.getEmail());
    System.out.println(comment.getComment());
  }

  @Test
  void testFindAll() {
    List<Comment> comments = commentRepository.findAll();
    System.out.println(comments.size());
  }

  @Test
  void testFindAllByEmail() {
    List<Comment> comments = commentRepository.findAllByEmail("admin@gmail.com");
    System.out.println(comments.size());
  }

}
