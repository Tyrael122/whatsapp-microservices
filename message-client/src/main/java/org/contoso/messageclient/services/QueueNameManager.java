package org.contoso.messageclient.services;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class QueueNameManager {
    private static final String MAP_FILE = "queue_map.txt";

    /** Checks if a queue name exists for a given instance ID */
    public static String getQueueName(String instanceId) {
        try {
            Map<String, String> queueMap = loadQueueMap();
            return queueMap.get(instanceId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Saves a queue name for a given instance ID */
    public static void saveQueueName(String instanceId, String queueName) {
        try {
            Map<String, String> queueMap = loadQueueMap();
            queueMap.put(instanceId, queueName);
            saveQueueMap(queueMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, String> loadQueueMap() throws IOException {
        Map<String, String> queueMap = new HashMap<>();
        File file = new File(MAP_FILE);

        if (!file.exists()) {
            return queueMap;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    queueMap.put(parts[0], parts[1]);
                }
            }
        }
        return queueMap;
    }

    private static void saveQueueMap(Map<String, String> queueMap) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MAP_FILE))) {
            for (Map.Entry<String, String> entry : queueMap.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        }
    }
}
