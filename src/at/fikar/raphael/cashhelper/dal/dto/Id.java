package at.fikar.raphael.cashhelper.dal.dto;

public class Id<T> {

    private final long rawId;

    public Id(final long rawId){
        this.rawId = rawId;
    }

    public long getRaw(){
        return rawId;
    }
}
