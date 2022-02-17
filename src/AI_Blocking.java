import java.util.ArrayList;

public class AI_Blocking extends AI_Build {
    public AI_Blocking(int x) {
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
    public Location getMoveHelper(char[][] [] board, char l) {
        //if 3 me finish it
        doubtrip linfo = info(l, board);
        if (linfo.quadpotential.size() != 0) {
            int[][] line = linfo.quadpotential.get(0);
            for (int x = 0; x < line.length; x++) {
                if (board[line[x][0]][line[x][1]][line[x][2]] == '-')
                    return new Location(line[x][2], line[x][1], line[x][0]);
            }
        }
        doubtrip notlinfo = info(((l == 'x')?'o':'x'), board);
        if (notlinfo.quadpotential.size() != 0) {
            int[][] line = notlinfo.quadpotential.get(0);
            for (int x = 0; x < line.length; x++) {
                if (board[line[x][0]][line[x][1]][line[x][2]] == '-')
                    return new Location(line[x][2],line[x][1],line[x][0]);
            }
        }
        ArrayList<int[]> in = new ArrayList<>();
        int highcountdoubles = 0; int highscountsingle = 0; int highcountempty = 0;
        int[] finalspot = null;
        if (notlinfo.tripotential.size() != 0) {
            for (int i = 0; i < notlinfo.tripotential.size(); i++) {
                int[][] line = notlinfo.tripotential.get(i);
                for (int x = 0; x < line.length; x++) {
                    if (board[line[x][0]][line[x][1]][line[x][2]] == '-')
                        in.add(line[x]);
                }
            }
            if (in.size() != 0) {
                finalspot = in.get((int) (Math.random() * in.size()));
                for (int i = 0; i < in.size(); i++) {
                    int[] spot = in.get(i);
                    int counter = 0;
                    for (int j = 0; j < in.size(); j++) {
                        if (spot[0] == in.get(j)[0] && spot[1] == in.get(j)[1] && spot[2] == in.get(j)[2]) {
                            counter++;
                        }
                    }
                    if (counter > highcountdoubles) {
                        highcountdoubles = counter;
                        finalspot = spot;
                    }
                }
            }
        }
        in = new ArrayList<>();
        int highcountdoubles2 = 0; int highscountsingle2 = 0; int highcountempty2 = 0;
        int[] finalspot2 = null;
        if (linfo.tripotential.size() != 0) {
            for (int i = 0; i < linfo.tripotential.size(); i++) {
                int[][] line = linfo.tripotential.get(i);
                for (int x = 0; x < line.length; x++) {
                    if (board[line[x][0]][line[x][1]][line[x][2]] == '-')
                        in.add(line[x]);
                }
            }
            if (in.size() != 0) {
                finalspot2 = in.get((int) (Math.random()*in.size()));
                for (int i = 0; i < in.size(); i++) {
                    int[] spot = in.get(i); int counter = 0;
                    for (int j = 0; j < in.size(); j++) {
                        if (spot[0] == in.get(j)[0] && spot[1] == in.get(j)[1] && spot[2] == in.get(j)[2]) {
                            counter++;
                        }
                    }
                    if (counter > highcountdoubles2) {
                        highcountdoubles2 = counter;
                        finalspot2 = spot;
                    }
                }
            }
        }//TODO: for now it's random of all highest weighted, need more educated decision, spot that increases my score and decreases opp's score
        if (highcountdoubles2 > 1) {
            return new Location(finalspot2[2],finalspot2[1],finalspot2[0]);
        }
        else if (highcountdoubles > 1) {
            return new Location(finalspot[2],finalspot[1],finalspot[0]);
        }
        int scorel;
        int scorenotl;

        if (highcountdoubles > highcountdoubles2) {
            return new Location(finalspot[2],finalspot[1],finalspot[0]);
        }
        if (highcountdoubles2 > highcountdoubles) {
            return new Location(finalspot2[2],finalspot2[1],finalspot2[0]);
        }
        if (highcountdoubles != 0) {
            //TODO: for now it's always offense, needs more educated decision
            return new Location(finalspot2[2],finalspot2[1],finalspot2[0]);
        }
        //TODO: add similar logic as above but for singles
        int x, y, z;
        do {
            x = (int) (Math.random()*4);
            y = (int) (Math.random()*4);
            z = (int) (Math.random()*4);
        } while (board[z][y][x] != '-');
        return new Location(x,y,z);
    }

    public int[][] almostWinningMoves(char c, char[][][] board) {
        //checking for 3 in line for given char
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
                if (counter == 3 && dashcounter == 1)
                    return new int[][]{{x, y, 0},{x,y,1},{x,y,2},{x,y,3}};

                counter = dashcounter = 0;
                for (int z = 0; z < board.length; z++) {
                    if (board[x][z][y] == c)
                        counter++;
                    if (board[x][z][y] == '-')
                        dashcounter++;
                }
                if (counter == 3 && dashcounter == 1)
                    return new int[][]{{x, 0, y},{x,1,y},{x,2,y},{x,3,y}};

                counter = dashcounter = 0;
                for (int z = 0; z < board.length; z++) {
                    if (board[z][x][y] == c)
                        counter++;
                    if (board[z][x][y] == '-')
                        dashcounter++;
                }
                if (counter == 3 && dashcounter == 1)
                    return new int[][]{{0, x, y},{1,x,y},{2,x,y},{3,x,y}};
            } //vertical (16), horizontal (16) on plane, multi-plane straight (16)

        for (int x = 0; x < board.length; x++) {
            counter = dashcounter = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[x][i][i] == c)
                    counter++;
                if (board[x][i][i] == '-')
                    dashcounter++;
            }
            if (counter == 3 && dashcounter == 1)
                return new int[][]{{x, 0, 0},{x,1,1},{x,2,2},{x,3,3}};
            counter = dashcounter = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[x][3-i][i] == c)
                    counter++;
                if (board[x][3-i][i] == '-')
                    dashcounter++;
            }
            if (counter == 3 && dashcounter == 1)
                return new int[][]{{x, 3, 0},{x,2,1},{x,1,2},{x,0,3}};
            counter = dashcounter = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[i][x][i] == c)
                    counter++;
                if (board[i][x][i] == '-')
                    dashcounter++;
            }
            if (counter == 3 && dashcounter == 1)
                return new int[][]{{0, x, 0},{1,x,1},{2,x,2},{3,x,3}};
            counter = dashcounter = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[3-i][x][i] == c)
                    counter++;
                if (board[3-1][x][i] == '-')
                    dashcounter++;
            }
            if (counter == 3 && dashcounter == 1)
                return new int[][]{{3, x, 0},{2,x,1},{1,x,2},{0,x,3}};
            counter = dashcounter = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[i][i][x] == c)
                    counter++;
                if (board[i][i][x] == '-')
                    dashcounter++;
            }
            if (counter == 3 && dashcounter == 1)
                return new int[][]{{0, 0, x},{1,1,x},{2,2,x},{3,3,x}};
            counter = dashcounter = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[3-i][i][x] == c)
                    counter++;
                if (board[3-i][i][x] == '-')
                    dashcounter++;
            }
            if (counter == 3 && dashcounter == 1)
                return new int[][]{{3, 0, x},{2,1,x},{1,2,x},{0,3,x}};
        } //diagonal (8) on plane, multi-plane diagonal (16)
        counter = dashcounter = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][i][i] == c)
                counter++;
            if (board[i][i][i] == '-')
                dashcounter++;
        }
        if (counter == 3 && dashcounter == 1)
            return new int[][]{{0, 0, 0},{1,1,1},{2,2,2},{3,3,3}};
        counter = dashcounter = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][i][3-i] == c)
                counter++;
            if (board[i][i][3-i] == '-')
                dashcounter++;
        }
        if (counter == 3 && dashcounter == 1)
            return new int[][]{{0, 0, 3},{1,1,2},{2,2,1},{3,3,0}};
        counter = dashcounter = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][3-i][i] == c)
                counter++;
            if (board[i][3-i][i] == '-')
                dashcounter++;
        }
        if (counter == 3 && dashcounter == 1)
            return new int[][]{{0, 3, 0},{1,2,1},{2,1,2},{3,0,3}};
        counter = dashcounter = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[3-i][i][i] == c)
                counter++;
            if (board[3-i][i][i] == '-')
                dashcounter++;
        }
        if (counter == 3 && dashcounter == 1)
            return new int[][]{{3, 0, 0},{2,1,1},{1,2,2},{0,3,3}};
        // multi-multi-plane diagonal (4)
        return null;
    }

    public doubtrip info(char c, char[][] [] board) {
        doubtrip info = new doubtrip(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0,0,0, 0);
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
                    info.triples++;
                }
                if (counter == 2 && dashcounter == 2) {
                    info.tripotential.add(new int[][]{{x, y, 0}, {x, y, 1}, {x, y, 2}, {x, y, 3}});
                    info.pairs++;
                }
                if (counter == 1 && dashcounter == 3) {
                    info.doublepotential.add(new int[][]{{x, y, 0}, {x, y, 1}, {x, y, 2}, {x, y, 3}});
                    info.singles++;
                }
                if (counter == 0 && dashcounter == 4) {
                    info.singlepotential.add(new int[][]{{x, y, 0}, {x, y, 1}, {x, y, 2}, {x, y, 3}});
                    info.empties++;
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
                    info.triples++;
                }
                if (counter == 2 && dashcounter == 2) {
                    info.tripotential.add(new int[][]{{x, 0, y}, {x, 1, y}, {x, 2, y}, {x, 3, y}});
                    info.pairs++;
                }
                if (counter == 1 && dashcounter == 3) {
                    info.doublepotential.add(new int[][]{{x, 0, y}, {x, 1, y}, {x, 2, y}, {x, 3, y}});
                    info.singles++;
                }
                if (counter == 0 && dashcounter == 4) {
                    info.singlepotential.add(new int[][]{{x, 0, y}, {x, 1, y}, {x, 2, y}, {x, 3, y}});
                    info.empties++;
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
                    info.triples++;
                }
                if (counter == 2 && dashcounter == 2) {
                    info.tripotential.add(new int[][]{{0, x, y}, {1, x, y}, {2, x, y}, {3, x, y}});
                    info.pairs++;
                }
                if (counter == 1 && dashcounter == 3) {
                    info.doublepotential.add(new int[][]{{0, x, y}, {1, x, y}, {2, x, y}, {3, x, y}});
                    info.singles++;
                }
                if (counter == 1 && dashcounter == 3) {
                    info.singlepotential.add(new int[][]{{0, x, y}, {1, x, y}, {2, x, y}, {3, x, y}});
                    info.empties++;
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
                info.triples++;
            }
            if (counter == 2 && dashcounter == 2) {
                info.tripotential.add(new int[][]{{x, 0, 0}, {x, 1, 1}, {x, 2, 2}, {x, 3, 3}});
                info.pairs++;
            }
            if (counter == 1 && dashcounter == 3) {
                info.doublepotential.add(new int[][]{{x, 0, 0}, {x, 1, 1}, {x, 2, 2}, {x, 3, 3}});
                info.singles++;
            }
            if (counter == 0 && dashcounter == 4) {
                info.singlepotential.add(new int[][]{{x, 0, 0}, {x, 1, 1}, {x, 2, 2}, {x, 3, 3}});
                info.empties++;
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
                info.triples++;
            }
            if (counter == 2 && dashcounter == 2) {
                info.tripotential.add(new int[][]{{x, 3, 0}, {x, 2, 1}, {x, 1, 2}, {x, 0, 3}});
                info.pairs++;
            }
            if (counter == 1 && dashcounter == 3) {
                info.doublepotential.add(new int[][]{{x, 3, 0}, {x, 2, 1}, {x, 1, 2}, {x, 0, 3}});
                info.singles++;
            }
            if (counter == 1 && dashcounter == 3) {
                info.singlepotential.add(new int[][]{{x, 0, 0}, {x, 1, 1}, {x, 2, 2}, {x, 3, 3}});
                info.empties++;
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
                info.triples++;
            }
            if (counter == 2 && dashcounter == 2) {
                info.tripotential.add(new int[][]{{0, x, 0}, {1, x, 1}, {2, x, 2}, {3, x, 3}});
                info.pairs++;
            }
            if (counter == 1 && dashcounter == 3) {
                info.doublepotential.add(new int[][]{{0, x, 0}, {1, x, 1}, {2, x, 2}, {3, x, 3}});
                info.singles++;
            }
            if (counter == 0 && dashcounter == 4) {
                info.singlepotential.add(new int[][]{{0, x, 0}, {1, x, 1}, {2, x, 2}, {3, x, 3}});
                info.empties++;
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
                info.triples++;
            }
            if (counter == 2 && dashcounter == 2) {
                info.tripotential.add(new int[][]{{3, x, 0}, {2, x, 1}, {1, x, 2}, {0, x, 3}});
                info.pairs++;
            }
            if (counter == 1 && dashcounter == 3) {
                info.doublepotential.add(new int[][]{{3, x, 0}, {2, x, 1}, {1, x, 2}, {0, x, 3}});
                info.singles++;
            }
            if (counter == 0 && dashcounter == 4) {
                info.singlepotential.add(new int[][]{{3, x, 0}, {2, x, 1}, {1, x, 2}, {0, x, 3}});
                info.empties++;
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
                info.triples++;
            }
            if (counter == 2 && dashcounter == 2) {
                info.tripotential.add(new int[][]{{0, 0, x}, {1, 1, x}, {2, 2, x}, {3, 3, x}});
                info.pairs++;
            }
            if (counter == 1 && dashcounter == 3) {
                info.doublepotential.add(new int[][]{{0, 0, x}, {1, 1, x}, {2, 2, x}, {3, 3, x}});
                info.singles++;
            }
            if (counter == 0 && dashcounter == 4) {
                info.singlepotential.add(new int[][]{{0, 0, x}, {1, 1, x}, {2, 2, x}, {3, 3, x}});
                info.empties++;
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
                info.triples++;
            }
            if (counter == 2 && dashcounter == 2) {
                info.tripotential.add(new int[][]{{3, 0, x}, {2, 1, x}, {1, 2, x}, {0, 3, x}});
                info.pairs++;
            }
            if (counter == 1 && dashcounter == 3) {
                info.doublepotential.add(new int[][]{{3, 0, x}, {2, 1, x}, {1, 2, x}, {0, 3, x}});
                info.singles++;
            }
            if (counter == 0 && dashcounter == 4) {
                info.singlepotential.add(new int[][]{{3, 0, x}, {2, 1, x}, {1, 2, x}, {0, 3, x}});
                info.empties++;
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
            info.triples++;
        }
        if (counter == 2 && dashcounter == 2) {
            info.tripotential.add(new int[][]{{0, 0, 0}, {1, 1, 1}, {2, 2, 2}, {3, 3, 3}});
            info.pairs++;
        }
        if (counter == 1 && dashcounter == 3) {
            info.doublepotential.add(new int[][]{{0, 0, 0}, {1, 1, 1}, {2, 2, 2}, {3, 3, 3}});
            info.singles++;
        }
        if (counter == 0 && dashcounter == 4) {
            info.singlepotential.add(new int[][]{{0, 0, 0}, {1, 1, 1}, {2, 2, 2}, {3, 3, 3}});
            info.empties++;
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
            info.triples++;
        }
        if (counter == 2 && dashcounter == 2) {
            info.tripotential.add(new int[][]{{0, 0, 3}, {1, 1, 2}, {2, 2, 1}, {3, 3, 0}});
            info.pairs++;
        }
        if (counter == 1 && dashcounter == 3) {
            info.doublepotential.add(new int[][]{{0, 0, 3}, {1, 1, 2}, {2, 2, 1}, {3, 3, 0}});
            info.singles++;
        }
        if (counter == 0 && dashcounter == 4) {
            info.singlepotential.add(new int[][]{{0, 0, 3}, {1, 1, 2}, {2, 2, 1}, {3, 3, 0}});
            info.empties++;
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
            info.triples++;
        }
        if (counter == 2 && dashcounter == 2) {
            info.tripotential.add(new int[][]{{0, 3, 0}, {1, 2, 1}, {2, 1, 2}, {3, 0, 3}});
            info.pairs++;
        }
        if (counter == 1 && dashcounter == 3) {
            info.doublepotential.add(new int[][]{{0, 3, 0}, {1, 2, 1}, {2, 1, 2}, {3, 0, 3}});
            info.singles++;
        }
        if (counter == 0 && dashcounter == 4) {
            info.singlepotential.add(new int[][]{{0, 3, 0}, {1, 2, 1}, {2, 1, 2}, {3, 0, 3}});
            info.empties++;
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
            info.triples++;
        }
        if (counter == 2 && dashcounter == 2) {
            info.tripotential.add(new int[][]{{3, 0, 0}, {2, 1, 1}, {1, 2, 2}, {0, 3, 3}});
            info.pairs++;
        }
        if (counter == 1 && dashcounter == 3) {
            info.doublepotential.add(new int[][]{{0, 3, 0}, {1, 2, 1}, {2, 1, 2}, {3, 0, 3}});
            info.singles++;
        }
        if (counter == 0 && dashcounter == 4) {
            info.singlepotential.add(new int[][]{{0, 3, 0}, {1, 2, 1}, {2, 1, 2}, {3, 0, 3}});
            info.empties++;
        }
        // multi-multi-plane diagonal (4)
        return info;
    }
}
class doubtrip {
    ArrayList<int[][]> quadpotential, tripotential, doublepotential, singlepotential;
    int triples, pairs, singles, empties;

    public doubtrip(ArrayList<int[][]> quadpotential, ArrayList<int[][]> tripotential, ArrayList<int[][]> doublepotential, ArrayList<int[][]> singlepotential, int triples, int pairs, int singles, int empties) {
        this.quadpotential = quadpotential;
        this.tripotential = tripotential;
        this.doublepotential = doublepotential;
        this.singlepotential = singlepotential;
        this.triples = triples;
        this.pairs = pairs;
        this.singles = singles;
        this.empties = empties;
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