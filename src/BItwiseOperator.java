


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class BitwiseOperator extends JFrame {
    // Main method to start the game
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new  BitwiseOperator().setVisible(true);
        });
    }

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private GamePanel gamePanel;

  
    public  BitwiseOperator() {
        setTitle("2D Action Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        gamePanel = new GamePanel(WIDTH, HEIGHT);
        add(gamePanel);
        addKeyListener(gamePanel);
    }

    // Panel where the game is rendered
    private static class GamePanel extends JPanel implements KeyListener, Runnable {
        private static final int PLAYER_WIDTH = 50;
        private static final int PLAYER_HEIGHT = 50;
        private static final int ENEMY_WIDTH = 40;
        private static final int ENEMY_HEIGHT = 40;
        private static final int PROJECTILE_SIZE = 10;
        private static final int GROUND_HEIGHT = 50;

        private final int panelWidth;
        private final int panelHeight;
        private Player player;
        private List<Enemy> enemies;
        private List<Projectile> projectiles;
        private Thread gameThread;
        private boolean running;
        private int score;
        private Random random;

        public GamePanel(int width, int height) {
            this.panelWidth = width;
            this.panelHeight = height;
            
            setFocusable(true);
            setBackground(Color.BLACK);
            
            // Initialize game objects
            player = new Player(100, height - GROUND_HEIGHT - PLAYER_HEIGHT, width);
            enemies = new ArrayList<>();
            projectiles = new ArrayList<>();
            random = new Random();
            score = 0;
            
            // Start the game loop
            running = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        @Override
        public void run() {
            // Game loop
            long lastTime = System.nanoTime();
            double amountOfTicks = 60.0;
            double ns = 1000000000 / amountOfTicks;
            double delta = 0;
            long timer = System.currentTimeMillis();
            int frames = 0;

            while (running) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                
                while (delta >= 1) {
                    update();
                    delta--;
                }
                
                repaint();
                frames++;

                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    System.out.println("FPS: " + frames);
                    frames = 0;
                    
                    // Spawn enemies occasionally
                    if (random.nextInt(100) < 30) {
                        spawnEnemy();
                    }
                }

                // Simple frame limiting
                try {
                    Thread.sleep(16); // ~60 FPS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void update() {
            // Update player
            player.update();
            
            // Check if player is on ground
            if (player.y + PLAYER_HEIGHT >= panelHeight - GROUND_HEIGHT) {
                player.y = panelHeight - GROUND_HEIGHT - PLAYER_HEIGHT;
                player.jumping = false;
                player.velY = 0;
            }
            
            // Update enemies
            for (Enemy enemy : enemies) {
                enemy.update();
            }
            
            // Update projectiles
            Iterator<Projectile> it = projectiles.iterator();
            while (it.hasNext()) {
                Projectile projectile = it.next();
                projectile.update();
                
                // Remove projectiles that go off screen
                if (projectile.x < 0 || projectile.x > panelWidth) {
                    it.remove();
                    continue;
                }
                
                // Check collision with enemies
                Iterator<Enemy> enemyIt = enemies.iterator();
                while (enemyIt.hasNext()) {
                    Enemy enemy = enemyIt.next();
                    if (collision(projectile.x, projectile.y, PROJECTILE_SIZE, PROJECTILE_SIZE,
                                 enemy.x, enemy.y, ENEMY_WIDTH, ENEMY_HEIGHT)) {
                        it.remove();
                        enemyIt.remove();
                        score += 10;
                        break;
                    }
                }
            }
            
            // Check collision between player and enemies
            Iterator<Enemy> enemyIt = enemies.iterator();
            while (enemyIt.hasNext()) {
                Enemy enemy = enemyIt.next();
                
                // Remove enemies that go off screen
                if (enemy.x + ENEMY_WIDTH < 0) {
                    enemyIt.remove();
                    continue;
                }
                
                // Check collision with player
                if (collision(player.x, player.y, PLAYER_WIDTH, PLAYER_HEIGHT,
                             enemy.x, enemy.y, ENEMY_WIDTH, ENEMY_HEIGHT)) {
                    // Game over logic can be added here
                    System.out.println("Game Over! Score: " + score);
                    resetGame();
                    break;
                }
            }
        }
        
        private boolean collision(double x1, double y1, int width1, int height1,
                                 double x2, double y2, int width2, int height2) {
            return x1 < x2 + width2 &&
                   x1 + width1 > x2 &&
                   y1 < y2 + height2 &&
                   y1 + height1 > y2;
        }
        
        private void spawnEnemy() {
            int y = panelHeight - GROUND_HEIGHT - ENEMY_HEIGHT;
            if (random.nextBoolean()) {
                // Flying enemy
                y = panelHeight - GROUND_HEIGHT - ENEMY_HEIGHT - random.nextInt(200);
            }
            Enemy enemy = new Enemy(panelWidth, y);
            enemies.add(enemy);
        }
        
        private void resetGame() {
            player = new Player(100, panelHeight - GROUND_HEIGHT - PLAYER_HEIGHT, panelWidth);
            enemies.clear();
            projectiles.clear();
            score = 0;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // Draw ground
            g.setColor(Color.GREEN);
            g.fillRect(0, panelHeight - GROUND_HEIGHT, panelWidth, GROUND_HEIGHT);
            
            // Draw player
            g.setColor(Color.BLUE);
            g.fillRect((int)player.x, (int)player.y, PLAYER_WIDTH, PLAYER_HEIGHT);
            
            // Draw enemies
            g.setColor(Color.RED);
            for (Enemy enemy : enemies) {
                g.fillRect((int)enemy.x, (int)enemy.y, ENEMY_WIDTH, ENEMY_HEIGHT);
            }
            
            // Draw projectiles
            g.setColor(Color.YELLOW);
            for (Projectile projectile : projectiles) {
                g.fillRect((int)projectile.x, (int)projectile.y, PROJECTILE_SIZE, PROJECTILE_SIZE);
            }
            
            // Draw score
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score: " + score, 20, 30);
            
            // Draw controls info
            g.setFont(new Font("Arial", Font.PLAIN, 14));
            g.drawString("Controls: Arrow Keys to move, Space to shoot", panelWidth - 350, 20);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                player.left = true;
            }
            if (key == KeyEvent.VK_RIGHT) {
                player.right = true;
            }
            if (key == KeyEvent.VK_UP && !player.jumping) {
                player.jumping = true;
                player.velY = -15;
            }
            if (key == KeyEvent.VK_SPACE) {
                // Shoot projectile
                Projectile projectile = new Projectile(
                    player.x + PLAYER_WIDTH / 2 - PROJECTILE_SIZE / 2,
                    player.y + PLAYER_HEIGHT / 2 - PROJECTILE_SIZE / 2,
                    player.facing * 10
                );
                projectiles.add(projectile);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                player.left = false;
            }
            if (key == KeyEvent.VK_RIGHT) {
                player.right = false;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // Not used
        }
    }

    // Game entity classes
    private static class Player {
        double x, y;
        double velX, velY;
        boolean left, right, jumping;
        int facing; // 1 for right, -1 for left
        private final int maxWidth;

        public Player(double x, double y, int maxWidth) {
            this.x = x;
            this.y = y;
            this.velX = 0;
            this.velY = 0;
            this.left = false;
            this.right = false;
            this.jumping = false;
            this.facing = 1;
            this.maxWidth = maxWidth;
        }

        public void update() {
            // Handle movement
            if (left) {
                velX = -5;
                facing = -1;
            } else if (right) {
                velX = 5;
                facing = 1;
            } else {
                velX = 0;
            }
            
            // Apply gravity
            velY += 0.8;
            
            // Update position
            x += velX;
            y += velY;
            
            // Keep player within bounds
            if (x < 0) {
                x = 0;
            } else if (x > maxWidth - GamePanel.PLAYER_WIDTH) {
                x = maxWidth - GamePanel.PLAYER_WIDTH;
            }
        }
    }

    private static class Enemy {
        double x, y;
        double velX, velY;

        public Enemy(double x, double y) {
            this.x = x;
            this.y = y;
            this.velX = -3;
            
            // Some enemies will bob up and down
            this.velY = Math.random() < 0.3 ? Math.sin(x) : 0;
        }

        public void update() {
            x += velX;
            y += velY;
        }
    }

    private static class Projectile {
        double x, y;
        double velX;

        public Projectile(double x, double y, double velX) {
            this.x = x;
            this.y = y;
            this.velX = velX;
        }

        public void update() {
            x += velX;
        }
    }
}

