/**
 * Copyright (c) 2016, The National Archives <pronom@nationalarchives.gsi.gov.uk>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following
 * conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of the The National Archives nor the
 *    names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package uk.gov.nationalarchives.droid.core.interfaces.archive;

import java.util.HashMap;
import java.util.Map;

/**
 * Resolves format PUIDS to an archive format type which can be handled.
 * @author rflitcroft
 *
 */
public class ArchiveFormatResolverImpl implements ArchiveFormatResolver {

    private final Map<String, String> puidsMap = new HashMap<String, String>();

    /**
     * {@inheritDoc}
     */
    @Override
    public final String forPuid(String puid) {
        return puidsMap.get(puid);
    }
    
    /**
     * Sets the format -&gt; puid mapping.
     * 
     * @param puids the PUIDS to set
     */
    public final void setPuids(Map<String, String> puids) {
        for (Map.Entry<String, String> entry : puids.entrySet()) {
            final String[] puidsIn = entry.getValue().split(",");
            for (String puid : puidsIn) {
                puidsMap.put(puid.trim(), entry.getKey());
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final void registerPuid(String puid, String format) {
        puidsMap.put(puid, format);
    }
    
}
