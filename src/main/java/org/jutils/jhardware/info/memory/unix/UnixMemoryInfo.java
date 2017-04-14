/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jutils.jhardware.info.memory.unix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jutils.jhardware.info.memory.AbstractMemoryInfo;
import org.jutils.jhardware.util.HardwareInfoUtils;

/**
 * Information related to Memory
 * 
 * @author Javier Garcia Alonso
 */
public final class UnixMemoryInfo extends AbstractMemoryInfo {
    private static final String MEMINFO = "/proc/meminfo";
    
    private static String getMemoryData(){
        List<String> streamMemoryInfo = HardwareInfoUtils.readFile(MEMINFO);
        final StringBuilder buffer = new StringBuilder();

        for(String line: streamMemoryInfo) {
            buffer.append(line).append("\r\n");
        }
        
        return buffer.toString();
    }

    @Override
    protected Map<String, String> parseInfo() {
        Map<String, String> memoryDataMap = new HashMap<>();
        String[] dataStringLines = getMemoryData().split("\\r?\\n");

        for (final String dataLine : dataStringLines) {
            String[] dataStringInfo = dataLine.split(":");
            memoryDataMap.put(dataStringInfo[0].trim(), (dataStringInfo.length == 2) ? dataStringInfo[1].trim() : "");
        }

        return memoryDataMap;
    }    
}
