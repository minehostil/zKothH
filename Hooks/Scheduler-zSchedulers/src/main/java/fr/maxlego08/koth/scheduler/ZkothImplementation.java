package fr.maxlego08.koth.scheduler;

import fr.maxlego08.koth.api.Koth;
import fr.maxlego08.koth.api.KothPlugin;
import fr.maxlego08.zscheduler.api.Implementation;
import fr.maxlego08.zscheduler.api.Scheduler;
import fr.maxlego08.zscheduler.api.SchedulerManager;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;

public class ZkothImplementation implements Implementation {

    private final KothPlugin plugin;

    public ZkothImplementation(KothPlugin plugin) {
        super();
        this.plugin = plugin;
    }

    public void register() {
        SchedulerManager schedulerManager = this.plugin.getProvider(SchedulerManager.class);
        schedulerManager.registerImplementation(this);
    }

    @Override
    public String getName() {
        return "ZKOTH";
    }

    @Override
    public void schedule(Scheduler scheduler) {
        String kothName = (String) scheduler.getImplementationValues().getOrDefault("koth_name", "");
        boolean startNow = (boolean) scheduler.getImplementationValues().getOrDefault("start_now", false);

        if (kothName.equalsIgnoreCase("random")) {
            List<Koth> koths = this.plugin.getKoths();
            if (koths.isEmpty()) {
                return;
            }
            Koth koth = koths.get(new Random().nextInt(koths.size()));
            koth.spawn(startNow);
            return;
        }

        Optional<Koth> optional = this.plugin.getKoth(kothName);
        if (optional.isPresent()) {
            Koth koth = optional.get();
            koth.spawn(startNow);
        } else {
            this.plugin.getLogger().log(Level.SEVERE, "Koth with name " + kothName + " was not found with scheduler " + scheduler.getName());
        }
    }

}
