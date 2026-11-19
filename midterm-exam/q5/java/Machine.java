import java.util.Locale;

/** A machine in the machine room. Two machines are equal when their ids are equal. */
public abstract class Machine implements Comparable<Machine> {
    private final String id;
    private final double capacityKg;
    private int busyUntil;                     // minutes after opening time
    private int jobs;
    private double totalKg;

    protected Machine(String id, double capacityKg) {
        // TODO(Q050): store id and capacityKg.
        this.id = id;
        this.capacityKg = capacityKg;
    }

    /** How long one cycle takes for this load; every kind of machine decides for itself. */
    protected abstract int cycleMinutes(double kg);

    /** Books the machine; returns the minute the job finishes. */
    public final int schedule(int start, double kg) throws ScheduleException {
        // TODO(Q050): reject kg<=0 or kg>capacityKg; reject start<busyUntil;
        //   busyUntil = start + cycleMinutes(kg); jobs++; totalKg += kg; return busyUntil;
        if (kg <= 0 || kg > capacityKg) {
            throw new ScheduleException("invalid load " + kg);
        }

        if (start < busyUntil) {
            throw new ScheduleException("machine busy until " + clock(busyUntil));
        }

        busyUntil = start + cycleMinutes(kg);
        jobs++;
        totalKg += kg;
        return busyUntil;
    }

    public String getId() {
        return id;
    }

    /** Minutes after 08:00 as a clock time. */
    static String clock(int minutes) {
        return String.format("%02d:%02d", 8 + minutes / 60, minutes % 60);
    }

    @Override
    public boolean equals(Object other) {
        // TODO(Q050): compare by id.
        if (this == other) {
            return true;
        }
        if (!(other instanceof Machine)) {
            return false;
        }
        Machine m = (Machine) other;
        return id.equals(m.id);
    }

    @Override
    public int hashCode() {
        // TODO(Q050): return id.hashCode();
        return id.hashCode();
    }

    @Override
    public int compareTo(Machine other) {
        // TODO(Q050): return id.compareTo(other.id);
        return id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s %-6s %d job(s) %5.1f kg, free at %s",
                id, getClass().getSimpleName(), jobs, totalKg, clock(busyUntil));
    }
}
