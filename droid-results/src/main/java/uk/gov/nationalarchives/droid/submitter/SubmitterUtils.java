/**
 * Copyright (c) 2012, The National Archives <pronom@nationalarchives.gsi.gov.uk>
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
package uk.gov.nationalarchives.droid.submitter;

import java.io.File;

/**
 * @author rflitcroft
 * 
 * Utilty class for file submission.
 *
 */
public final class SubmitterUtils {

    private SubmitterUtils() { }
    
    /**
     * Determines via best effort if the file system on which the file resides is available.
     * @param file a file
     * @param topLevelAbsolutePath  the top-level resource URI for the file.
     * @return true if the file system is available, false otherwise.
     */
    static boolean isFileSystemAvailable(final File file, final String topLevelAbsolutePath) {
        
        if (isEqualPath(file, topLevelAbsolutePath)) {
            return file.exists();
        } 
        
        return isFileSystemAvailable2(file, topLevelAbsolutePath);
    }
    
    private static boolean isFileSystemAvailable2(final File file, final String topLevelAbsolutePath) {
        
        boolean available;
        
        if (file.exists()) {
            available = true;
        } else {
            if (isEqualPath(file, topLevelAbsolutePath)) {
                available = false;
            } else {
                available = isFileSystemAvailable2(file.getParentFile(), topLevelAbsolutePath);
            }
        }
        
        return available;
    }
    
    private static boolean isEqualPath(final File file1, final String topLevelAbsolutePath) {
        return topLevelAbsolutePath.equals(file1.getAbsolutePath());
    }
    
}
