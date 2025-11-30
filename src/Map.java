//데이터 직렬화 예정
//위험하긴 해서 나중에 JSon 으로 바꿀 예정

import java.util.ArrayList;
import java.util.HashMap;

public record Map()
{
    public static HashMap<Position, Block> blockmap = new HashMap<Position, Block>();

    public static ArrayList<Position> entitypos = new ArrayList<Position>();
    public static ArrayList<Player> entitylist = new ArrayList<Player>();

    public Map()
    {
        Block block = new Block(true, true, false, false, BlockType.Wall);
        //초기 필드 상태 대입 예정
        blockmap.put(new Position(0,0), new Block(BlockType.Wall));
        blockmap.put(new Position(1,0), new Block(BlockType.Wall));
        blockmap.put(new Position(0,1), block);
        blockmap.put(new Position(1,1), block);





    }
}
