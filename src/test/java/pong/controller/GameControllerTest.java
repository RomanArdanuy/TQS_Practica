package pong.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import pong.model.Ball;
import pong.model.GameModel;
import pong.model.Paddle;
import pong.view.GameView;

public class GameControllerTest {
    
    @Mock
    private GameView mockView;
    
    @Mock
    private GameModel mockModel;
    
    private GameController controller;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new GameController(mockModel, mockView);
    }
    
    @Test
    void testUpdateCallsModelAndView() {
        // Setup mock model returns
        when(mockModel.getBall()).thenReturn(new Ball(100, 100, 10));
        when(mockModel.getLeftPaddle()).thenReturn(new Paddle(50, 100, 20, 100));
        when(mockModel.getRightPaddle()).thenReturn(new Paddle(730, 100, 20, 100));
        when(mockModel.getLeftScore()).thenReturn(0);
        when(mockModel.getRightScore()).thenReturn(0);
        
        // Call controller update
        controller.update();
        
        // Verify all interactions
        verify(mockModel).update();
        verify(mockView).updateBallPosition(anyDouble(), anyDouble());
        verify(mockView).updatePaddlePositions(anyDouble(), anyDouble());
        verify(mockView).updateScore(anyInt(), anyInt());
        verify(mockView).render();
    }
    
    @Test
    void testPaddleMovement() {
        // Test left paddle movement
        controller.setLeftPaddleVelocity(5.0);
        verify(mockModel.getLeftPaddle()).setVelocity(5.0);
        
        // Test right paddle movement
        controller.setRightPaddleVelocity(-5.0);
        verify(mockModel.getRightPaddle()).setVelocity(-5.0);
    }
}