package com.util;


import androidx.annotation.Nullable;

import java.util.Collection;

/**
  *@author oyzb
 */

public final class CollectionUtil {
   private CollectionUtil() {
   }

   public static boolean isEmpty(@Nullable Collection collection) {
       return null == collection || 0 == collection.size();
   }



}
