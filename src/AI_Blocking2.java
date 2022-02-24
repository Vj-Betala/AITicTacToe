import java.util.ArrayList;
import java.util.Arrays;

public class AI_Blocking2 extends AI_Build {
    public AI_Blocking2(int x) {
        super(x);
    }

    @Override
    public Location getMove(char[][] [] board) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                for (int z = 0; z < board[0][0].length; z++) {
                    if (board[x][y][z] != '-') {
                        return getMoveHelper(board, getLetter());
                    }
                }
            }
        }
        int x, y, z;
        do {
            x = (int) (Math.random()*2) + 1;
            y = (int) (Math.random()*2) + 1;
            z = (int) (Math.random()*2) + 1;
        } while (board[z][y][x] != '-');
        return new Location(x,y,z);
    }
    public Location getMoveHelper(char[][][] board, char l) {
        //if 3 me finish it
        doubtrip2 linfo = info(l, board);
        if (linfo.quadpotential.size() != 0) {
            int[][] line = linfo.quadpotential.get(0);
            for (int x = 0; x < line.length; x++) {
                if (board[line[x][0]][line[x][1]][line[x][2]] == '-')
                    return new Location(line[x][2], line[x][1], line[x][0]);
            }
        }
        doubtrip2 notlinfo = info(((l == 'x')?'o':'x'), board);
        if (notlinfo.quadpotential.size() != 0) {
            int[][] line = notlinfo.quadpotential.get(0);
            for (int x = 0; x < line.length; x++) {
                if (board[line[x][0]][line[x][1]][line[x][2]] == '-')
                    return new Location(line[x][2],line[x][1],line[x][0]);
            }
        }

        int boardscore = getBoardscore(linfo, notlinfo);
        ArrayList<moveObject> spots = new ArrayList<>();
        if (notlinfo.tripotential.size() != 0) {
            for (int i = 0; i < notlinfo.tripotential.size(); i++) {
                int[][] line = notlinfo.tripotential.get(i);
                for (int x = 0; x < line.length; x++) {
                    if (board[line[x][0]][line[x][1]][line[x][2]] == '-') {
                        boolean bruh = true;
                        for (int s = 0; s < spots.size(); s++)
                            if (spots.get(s).index != null && Arrays.equals(spots.get(s).index, line[x])) {
                                spots.get(s).opptriples++;
                                bruh = false;
                            }
                        if (bruh) {
                            spots.add(new moveObject(boardscore,line[x]));
                            spots.get(spots.size()-1).opptriples++;
                        }
                    }
                }
            }
        }
        if (linfo.tripotential.size() != 0) {
            for (int i = 0; i < linfo.tripotential.size(); i++) {
                int[][] line = linfo.tripotential.get(i);
                for (int x = 0; x < line.length; x++) {
                    if (board[line[x][0]][line[x][1]][line[x][2]] == '-') {
                        boolean bruh = true;
                        for (int s = 0; s < spots.size(); s++)
                            if (spots.get(s).index != null && Arrays.equals(spots.get(s).index, line[x])) {
                                spots.get(s).triples++;
                                bruh = false;
                            }
                        if (bruh) {
                            spots.add(new moveObject(boardscore,line[x]));
                            spots.get(spots.size()-1).triples++;
                        }
                    }
                }
            }
        }//TODO: for now it's random of all highest weighted, need more educated decision, spot that increases my score and decreases opp's score
        for (int x = 0; x < spots.size(); x++) {
            moveObject spot = spots.get(x);
            if (spots.get(x).triples > 1)
                return new Location(spot.index[2], spot.index[1], spot.index[0]);
        }//if i have move making double three
        for (int x = 0; x < spots.size(); x++) {
            moveObject spot = spots.get(x);
            if (spots.get(x).opptriples > 1)
                return new Location(spot.index[2], spot.index[1], spot.index[0]);
        }//if i can block move making double three

        if (notlinfo.doublepotential.size() != 0) {
            for (int i = 0; i < notlinfo.doublepotential.size(); i++) {
                int[][] line = notlinfo.doublepotential.get(i);
                for (int x = 0; x < line.length; x++) {
                    if (board[line[x][0]][line[x][1]][line[x][2]] == '-') {
                        boolean bruh = true;
                        for (int s = 0; s < spots.size(); s++)
                            if (spots.get(s).index != null && Arrays.equals(spots.get(s).index, line[x])) {
                                spots.get(s).opppairs++;
                                bruh = false;
                            }
                        if (bruh) {
                            spots.add(new moveObject(boardscore,line[x]));
                            spots.get(spots.size()-1).opppairs++;
                        }
                    }
                }
            }
        }
        if (linfo.doublepotential.size() != 0) {
            for (int i = 0; i < linfo.doublepotential.size(); i++) {
                int[][] line = linfo.doublepotential.get(i);
                for (int x = 0; x < line.length; x++) {
                    if (board[line[x][0]][line[x][1]][line[x][2]] == '-') {
                        boolean bruh = true;
                        for (int s = 0; s < spots.size(); s++)
                            if (spots.get(s).index != null && Arrays.equals(spots.get(s).index, line[x])) {
                                spots.get(s).pairs++;
                                bruh = false;
                            }
                        if (bruh) {
                            spots.add(new moveObject(boardscore,line[x]));
                            spots.get(spots.size()-1).pairs++;
                        }
                    }
                }
            }
        }

        if (notlinfo.singlepotential.size() != 0) {
            for (int i = 0; i < notlinfo.singlepotential.size(); i++) {
                int[][] line = notlinfo.singlepotential.get(i);
                for (int x = 0; x < line.length; x++) {
                    if (board[line[x][0]][line[x][1]][line[x][2]] == '-') {
                        boolean bruh = true;
                        for (int s = 0; s < spots.size(); s++)
                            if (spots.get(s).index != null && Arrays.equals(spots.get(s).index, line[x])) {
                                spots.get(s).oppsingles++;
                                bruh = false;
                            }
                        if (bruh) {
                            spots.add(new moveObject(boardscore,line[x]));
                            spots.get(spots.size()-1).oppsingles++;
                        }
                    }
                }
            }
        }
        if (linfo.singlepotential.size() != 0) {
            for (int i = 0; i < linfo.singlepotential.size(); i++) {
                int[][] line = linfo.singlepotential.get(i);
                for (int x = 0; x < line.length; x++) {
                    if (board[line[x][0]][line[x][1]][line[x][2]] == '-') {
                        boolean bruh = true;
                        for (int s = 0; s < spots.size(); s++)
                            if (spots.get(s).index != null && Arrays.equals(spots.get(s).index, line[x])) {
                                spots.get(s).singles++;
                                bruh = false;
                            }
                        if (bruh) {
                            spots.add(new moveObject(boardscore,line[x]));
                            spots.get(spots.size()-1).singles++;
                        }
                    }
                }
            }
        }

        if (spots.size() != 0) {
            moveObject spot = spots.get((int)(Math.random()*spots.size()));
            for (int x = 0; x < spots.size(); x++) {
                if (spots.get(x).getMoveScore() > spot.getMoveScore())
                    spot = spots.get(x);
            }
            return new Location(spot.index[2], spot.index[1], spot.index[0]);
        }
//below is junk
        //TODO: make recursion
        int x, y, z;
        do {
            x = (int) (Math.random()*4);
            y = (int) (Math.random()*4);
            z = (int) (Math.random()*4);
        } while (board[z][y][x] != '-');
        return new Location(x,y,z);
    }

    public int getBoardscore(doubtrip2 linfo, doubtrip2 notlinfo) {
        return linfo.getindBoardscore() - notlinfo.getindBoardscore();
    }

    public doubtrip2 info(char c, char[][][] board) {
        doubtrip2 info = new doubtrip2(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0,0,0, 0);
        //notes lines w/ triples, doubles, and singles w/ tally
        int counter, dashcounter;
        for (int x = 0; x < board.length; x++)
            for (int y = 0; y < board.length; y++) {
                counter = dashcounter = 0;
                for (int z = 0; z < board.length; z++) {
                    if (board[x][y][z] == c)
                        counter++;
                    if (board[x][y][z] == '-')
                        dashcounter++;
                }
                if (counter == 3 && dashcounter == 1) {
                    info.quadpotential.add(new int[][]{{x, y, 0}, {x, y, 1}, {x, y, 2}, {x, y, 3}});
                    info.setTriples(info.triples+1);
                }
                if (counter == 2 && dashcounter == 2) {
                    info.tripotential.add(new int[][]{{x, y, 0}, {x, y, 1}, {x, y, 2}, {x, y, 3}});
                    info.setPairs(info.pairs+1);
                }
                if (counter == 1 && dashcounter == 3) {
                    info.doublepotential.add(new int[][]{{x, y, 0}, {x, y, 1}, {x, y, 2}, {x, y, 3}});
                    info.setSingles(info.singles+1);
                }
                if (counter == 0 && dashcounter == 4) {
                    info.singlepotential.add(new int[][]{{x, y, 0}, {x, y, 1}, {x, y, 2}, {x, y, 3}});
                    info.setEmpties(info.empties+1);
                }
                counter = dashcounter = 0;
                for (int z = 0; z < board.length; z++) {
                    if (board[x][z][y] == c)
                        counter++;
                    if (board[x][z][y] == '-')
                        dashcounter++;
                }
                if (counter == 3 && dashcounter == 1) {
                    info.quadpotential.add(new int[][]{{x, 0, y}, {x, 1, y}, {x, 2, y}, {x, 3, y}});
                    info.setTriples(info.triples+1);
                }
                if (counter == 2 && dashcounter == 2) {
                    info.tripotential.add(new int[][]{{x, 0, y}, {x, 1, y}, {x, 2, y}, {x, 3, y}});
                    info.setPairs(info.pairs+1);
                }
                if (counter == 1 && dashcounter == 3) {
                    info.doublepotential.add(new int[][]{{x, 0, y}, {x, 1, y}, {x, 2, y}, {x, 3, y}});
                    info.setSingles(info.singles+1);
                }
                if (counter == 0 && dashcounter == 4) {
                    info.singlepotential.add(new int[][]{{x, 0, y}, {x, 1, y}, {x, 2, y}, {x, 3, y}});
                    info.setEmpties(info.empties+1);
                }
                counter = dashcounter = 0;
                for (int z = 0; z < board.length; z++) {
                    if (board[z][x][y] == c)
                        counter++;
                    if (board[z][x][y] == '-')
                        dashcounter++;
                }
                if (counter == 3 && dashcounter == 1) {
                    info.quadpotential.add(new int[][]{{0, x, y}, {1, x, y}, {2, x, y}, {3, x, y}});
                    info.setTriples(info.triples+1);
                }
                if (counter == 2 && dashcounter == 2) {
                    info.tripotential.add(new int[][]{{0, x, y}, {1, x, y}, {2, x, y}, {3, x, y}});
                    info.setPairs(info.pairs+1);
                }
                if (counter == 1 && dashcounter == 3) {
                    info.doublepotential.add(new int[][]{{0, x, y}, {1, x, y}, {2, x, y}, {3, x, y}});
                    info.setSingles(info.singles+1);
                }
                if (counter == 1 && dashcounter == 3) {
                    info.singlepotential.add(new int[][]{{0, x, y}, {1, x, y}, {2, x, y}, {3, x, y}});
                    info.setEmpties(info.empties+1);
                }
            } //vertical (16), horizontal (16) on plane, multi-plane straight (16)

        for (int x = 0; x < board.length; x++) {
            counter = dashcounter = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[x][i][i] == c)
                    counter++;
                if (board[x][i][i] == '-')
                    dashcounter++;
            }
            if (counter == 3 && dashcounter == 1) {
                info.quadpotential.add(new int[][]{{x, 0, 0}, {x, 1, 1}, {x, 2, 2}, {x, 3, 3}});
                info.setTriples(info.triples+1);
            }
            if (counter == 2 && dashcounter == 2) {
                info.tripotential.add(new int[][]{{x, 0, 0}, {x, 1, 1}, {x, 2, 2}, {x, 3, 3}});
                info.setPairs(info.pairs+1);
            }
            if (counter == 1 && dashcounter == 3) {
                info.doublepotential.add(new int[][]{{x, 0, 0}, {x, 1, 1}, {x, 2, 2}, {x, 3, 3}});
                info.setSingles(info.singles+1);
            }
            if (counter == 0 && dashcounter == 4) {
                info.singlepotential.add(new int[][]{{x, 0, 0}, {x, 1, 1}, {x, 2, 2}, {x, 3, 3}});
                info.setEmpties(info.empties+1);
            }
            counter = dashcounter = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[x][3-i][i] == c)
                    counter++;
                if (board[x][3-i][i] == '-')
                    dashcounter++;
            }
            if (counter == 3 && dashcounter == 1) {
                info.quadpotential.add(new int[][]{{x, 3, 0}, {x, 2, 1}, {x, 1, 2}, {x, 0, 3}});
                info.setTriples(info.triples+1);
            }
            if (counter == 2 && dashcounter == 2) {
                info.tripotential.add(new int[][]{{x, 3, 0}, {x, 2, 1}, {x, 1, 2}, {x, 0, 3}});
                info.setPairs(info.pairs+1);
            }
            if (counter == 1 && dashcounter == 3) {
                info.doublepotential.add(new int[][]{{x, 3, 0}, {x, 2, 1}, {x, 1, 2}, {x, 0, 3}});
                info.setSingles(info.singles+1);
            }
            if (counter == 1 && dashcounter == 3) {
                info.singlepotential.add(new int[][]{{x, 0, 0}, {x, 1, 1}, {x, 2, 2}, {x, 3, 3}});
                info.setEmpties(info.empties+1);
            }
            counter = dashcounter = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[i][x][i] == c)
                    counter++;
                if (board[i][x][i] == '-')
                    dashcounter++;
            }
            if (counter == 3 && dashcounter == 1) {
                info.quadpotential.add(new int[][]{{0, x, 0}, {1, x, 1}, {2, x, 2}, {3, x, 3}});
                info.setTriples(info.triples+1);
            }
            if (counter == 2 && dashcounter == 2) {
                info.tripotential.add(new int[][]{{0, x, 0}, {1, x, 1}, {2, x, 2}, {3, x, 3}});
                info.setPairs(info.pairs+1);
            }
            if (counter == 1 && dashcounter == 3) {
                info.doublepotential.add(new int[][]{{0, x, 0}, {1, x, 1}, {2, x, 2}, {3, x, 3}});
                info.setSingles(info.singles+1);
            }
            if (counter == 0 && dashcounter == 4) {
                info.singlepotential.add(new int[][]{{0, x, 0}, {1, x, 1}, {2, x, 2}, {3, x, 3}});
                info.setEmpties(info.empties+1);
            }
            counter = dashcounter = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[3-i][x][i] == c)
                    counter++;
                if (board[3-1][x][i] == '-')
                    dashcounter++;
            }
            if (counter == 3 && dashcounter == 1) {
                info.quadpotential.add(new int[][]{{3, x, 0}, {2, x, 1}, {1, x, 2}, {0, x, 3}});
                info.setTriples(info.triples+1);
            }
            if (counter == 2 && dashcounter == 2) {
                info.tripotential.add(new int[][]{{3, x, 0}, {2, x, 1}, {1, x, 2}, {0, x, 3}});
                info.setPairs(info.pairs+1);
            }
            if (counter == 1 && dashcounter == 3) {
                info.doublepotential.add(new int[][]{{3, x, 0}, {2, x, 1}, {1, x, 2}, {0, x, 3}});
                info.setSingles(info.singles+1);
            }
            if (counter == 0 && dashcounter == 4) {
                info.singlepotential.add(new int[][]{{3, x, 0}, {2, x, 1}, {1, x, 2}, {0, x, 3}});
                info.setEmpties(info.empties+1);
            }
            counter = dashcounter = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[i][i][x] == c)
                    counter++;
                if (board[i][i][x] == '-')
                    dashcounter++;
            }
            if (counter == 3 && dashcounter == 1) {
                info.quadpotential.add(new int[][]{{0, 0, x}, {1, 1, x}, {2, 2, x}, {3, 3, x}});
                info.setTriples(info.triples+1);
            }
            if (counter == 2 && dashcounter == 2) {
                info.tripotential.add(new int[][]{{0, 0, x}, {1, 1, x}, {2, 2, x}, {3, 3, x}});
                info.setPairs(info.pairs+1);
            }
            if (counter == 1 && dashcounter == 3) {
                info.doublepotential.add(new int[][]{{0, 0, x}, {1, 1, x}, {2, 2, x}, {3, 3, x}});
                info.setSingles(info.singles+1);
            }
            if (counter == 0 && dashcounter == 4) {
                info.singlepotential.add(new int[][]{{0, 0, x}, {1, 1, x}, {2, 2, x}, {3, 3, x}});
                info.setEmpties(info.empties+1);
            }
            counter = dashcounter = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[3-i][i][x] == c)
                    counter++;
                if (board[3-i][i][x] == '-')
                    dashcounter++;
            }
            if (counter == 3 && dashcounter == 1) {
                info.quadpotential.add(new int[][]{{3, 0, x}, {2, 1, x}, {1, 2, x}, {0, 3, x}});
                info.setTriples(info.triples+1);
            }
            if (counter == 2 && dashcounter == 2) {
                info.tripotential.add(new int[][]{{3, 0, x}, {2, 1, x}, {1, 2, x}, {0, 3, x}});
                info.setPairs(info.pairs+1);
            }
            if (counter == 1 && dashcounter == 3) {
                info.doublepotential.add(new int[][]{{3, 0, x}, {2, 1, x}, {1, 2, x}, {0, 3, x}});
                info.setSingles(info.singles+1);
            }
            if (counter == 0 && dashcounter == 4) {
                info.singlepotential.add(new int[][]{{3, 0, x}, {2, 1, x}, {1, 2, x}, {0, 3, x}});
                info.setEmpties(info.empties+1);
            }
        } //diagonal (8) on plane, multi-plane diagonal (16)
        counter = dashcounter = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][i][i] == c)
                counter++;
            if (board[i][i][i] == '-')
                dashcounter++;
        }
        if (counter == 3 && dashcounter == 1) {
            info.quadpotential.add(new int[][]{{0, 0, 0}, {1, 1, 1}, {2, 2, 2}, {3, 3, 3}});
            info.setTriples(info.triples+1);
        }
        if (counter == 2 && dashcounter == 2) {
            info.tripotential.add(new int[][]{{0, 0, 0}, {1, 1, 1}, {2, 2, 2}, {3, 3, 3}});
            info.setPairs(info.pairs+1);
        }
        if (counter == 1 && dashcounter == 3) {
            info.doublepotential.add(new int[][]{{0, 0, 0}, {1, 1, 1}, {2, 2, 2}, {3, 3, 3}});
            info.setSingles(info.singles+1);
        }
        if (counter == 0 && dashcounter == 4) {
            info.singlepotential.add(new int[][]{{0, 0, 0}, {1, 1, 1}, {2, 2, 2}, {3, 3, 3}});
            info.setEmpties(info.empties+1);
        }
        counter = dashcounter = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][i][3-i] == c)
                counter++;
            if (board[i][i][3-i] == '-')
                dashcounter++;
        }
        if (counter == 3 && dashcounter == 1) {
            info.quadpotential.add(new int[][]{{0, 0, 3}, {1, 1, 2}, {2, 2, 1}, {3, 3, 0}});
            info.setTriples(info.triples+1);
        }
        if (counter == 2 && dashcounter == 2) {
            info.tripotential.add(new int[][]{{0, 0, 3}, {1, 1, 2}, {2, 2, 1}, {3, 3, 0}});
            info.setPairs(info.pairs+1);
        }
        if (counter == 1 && dashcounter == 3) {
            info.doublepotential.add(new int[][]{{0, 0, 3}, {1, 1, 2}, {2, 2, 1}, {3, 3, 0}});
            info.setSingles(info.singles+1);
        }
        if (counter == 0 && dashcounter == 4) {
            info.singlepotential.add(new int[][]{{0, 0, 3}, {1, 1, 2}, {2, 2, 1}, {3, 3, 0}});
            info.setEmpties(info.empties+1);
        }
        counter = dashcounter = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][3-i][i] == c)
                counter++;
            if (board[i][3-i][i] == '-')
                dashcounter++;
        }
        if (counter == 3 && dashcounter == 1) {
            info.quadpotential.add(new int[][]{{0, 3, 0}, {1, 2, 1}, {2, 1, 2}, {3, 0, 3}});
            info.setTriples(info.triples+1);
        }
        if (counter == 2 && dashcounter == 2) {
            info.tripotential.add(new int[][]{{0, 3, 0}, {1, 2, 1}, {2, 1, 2}, {3, 0, 3}});
            info.setPairs(info.pairs+1);
        }
        if (counter == 1 && dashcounter == 3) {
            info.doublepotential.add(new int[][]{{0, 3, 0}, {1, 2, 1}, {2, 1, 2}, {3, 0, 3}});
            info.setSingles(info.singles+1);
        }
        if (counter == 0 && dashcounter == 4) {
            info.singlepotential.add(new int[][]{{0, 3, 0}, {1, 2, 1}, {2, 1, 2}, {3, 0, 3}});
            info.setEmpties(info.empties+1);
        }
        counter = dashcounter = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[3-i][i][i] == c)
                counter++;
            if (board[3-i][i][i] == '-')
                dashcounter++;
        }
        if (counter == 3 && dashcounter == 1) {
            info.quadpotential.add(new int[][]{{3, 0, 0}, {2, 1, 1}, {1, 2, 2}, {0, 3, 3}});
            info.setTriples(info.triples+1);
        }
        if (counter == 2 && dashcounter == 2) {
            info.tripotential.add(new int[][]{{3, 0, 0}, {2, 1, 1}, {1, 2, 2}, {0, 3, 3}});
            info.setPairs(info.pairs+1);
        }
        if (counter == 1 && dashcounter == 3) {
            info.doublepotential.add(new int[][]{{0, 3, 0}, {1, 2, 1}, {2, 1, 2}, {3, 0, 3}});
            info.setSingles(info.singles+1);
        }
        if (counter == 0 && dashcounter == 4) {
            info.singlepotential.add(new int[][]{{0, 3, 0}, {1, 2, 1}, {2, 1, 2}, {3, 0, 3}});
            info.setEmpties(info.empties+1);
        }
        // multi-multi-plane diagonal (4)
        return info;
    }
}
class doubtrip2 {
    ArrayList<int[][]> quadpotential, tripotential, doublepotential, singlepotential;
    int triples, pairs, singles, empties, boardscore;

    public doubtrip2(ArrayList<int[][]> quadpotential, ArrayList<int[][]> tripotential, ArrayList<int[][]> doublepotential, ArrayList<int[][]> singlepotential, int triples, int pairs, int singles, int empties) {
        this.quadpotential = quadpotential;
        this.tripotential = tripotential;
        this.doublepotential = doublepotential;
        this.singlepotential = singlepotential;
        this.triples = triples;
        this.pairs = pairs;
        this.singles = singles;
        this.empties = empties;
        boardscore = 0;
    }

    public void setTriples(int triples) {
        this.triples = triples;
        setindBoardscore();
    }

    public void setPairs(int pairs) {
        this.pairs = pairs;
        setindBoardscore();
    }

    public void setSingles(int singles) {
        this.singles = singles;
        setindBoardscore();
    }

    public void setEmpties(int empties) {
        this.empties = empties;
        setindBoardscore();
    }

    public void setindBoardscore() {
        if (triples > 0 || pairs > 1)
            boardscore = 10000;
        else
            boardscore = 3*pairs + 2*singles + empties;
        //boardscore  = (my player)'s score - (opponent)'s score for the board. move score = (new boardscore - boardscore)
        //boardscore = static boardscore (figure it out) if depth is 0, if not boardscore = higher boardscore of
        //if a player's score on player turn is 10000 then it overrides opposite players score, but if not then add.
        //TODO: find the right coeffecients for each subscore for the player.
    }

    public int getindBoardscore() {
        return boardscore;
    }

    @Override
    public String toString() {
        return "doubtrip{" +
                "quadpotential=" + quadpotential +
                ", tripotential=" + tripotential +
                ", doublepotential=" + doublepotential +
                ", singlepotential=" + singlepotential +
                ", triples=" + triples +
                ", pairs=" + pairs +
                ", singles=" + singles +
                ", empties=" + empties +
                '}';
    }
}
class moveObject {
    int triples, pairs, singles, moveScoreMy;
    int opptriples, opppairs, oppsingles, moveScoreOpp;
    int boardScore;
    int[] index;

    public moveObject(int boardScore, int[] index) {
        this.index = index;
        this.boardScore = boardScore;
    }

    public void setMoveScoreMy() {
        //if (triples > 0 || pairs > 1)
          //  moveScoreMy = 10000;
        //else
            moveScoreMy = 3*triples + 2*pairs + singles;
    }

    public void setMoveScoreOpp() {
        //if (triples > 0 || pairs > 1)
          //  moveScoreMy = 10000;
        //else
            moveScoreMy = 3*triples + 2*pairs + singles;
    }

    public int getMoveScoreMy() {
        return 3*triples + 2*pairs + singles;
    }

    public int getMoveScoreOpp() {
        return 3*opptriples + 2*opppairs + oppsingles;
    }

    public int getMoveScore() {
        return getMoveScoreMy() + getMoveScoreOpp();
    }

    public void setBoardScore(int boardScore) {
        this.boardScore = boardScore;
    }
}