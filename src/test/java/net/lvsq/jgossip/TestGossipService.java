package net.lvsq.jgossip;

import net.lvsq.jgossip.core.GossipService;
import net.lvsq.jgossip.core.GossipSettings;
import net.lvsq.jgossip.model.SeedMember;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestGossipService {

    @Test
    public void startGossip() throws Exception {
        String cluster = "testcluster";
        String ipAddress = "127.0.0.1";
        int port = 5000;
        //创建种子节点集合
        List<SeedMember> seedNodes = new ArrayList<>();
        //添加第一个种子节点
        SeedMember seed = new SeedMember();
        seed.setCluster(cluster);
        seed.setIpAddress(ipAddress);
        seed.setPort(port);
        seedNodes.add(seed);

        for (int i = 0; i < 1; i++) {
            GossipService gossipService = null;
            try {
                //TODO:为何端口号需要+i？
                //第一个i为0,所以与首个种子节点seed的端口号一致
                //但其后分别加i是为何？
                gossipService = new GossipService(cluster, ipAddress, port + i
                        , null, seedNodes, new GossipSettings()
                        , (member, state) -> {
                            System.out.println("member:" + member + "  state: " + state);
                        }
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            gossipService.start();
        }
    }
}
