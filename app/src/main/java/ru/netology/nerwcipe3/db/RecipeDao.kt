package ru.netology.nerwcipe3.db
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {
    // @Query("SELECT * FROM recipes ORDER BY id DESC")
    // fun getAll(): LiveData<List<RecipeEntity>>

    @Query("SELECT * FROM recipes ORDER BY id DESC")
    fun getAll(): LiveData<List<RecipeWithStages>>

//    @Query("""SELECT * FROM recipes WHERE id = :id
//        ORDER BY id DESC""")
//    fun getFavorites(): LiveData<List<RecipeWithStages>>

    @Query(
        """SELECT * FROM recipes,categories
WHERE id == recipe_id AND
category_Id IN (:categoryIds)
GROUP BY id
ORDER BY id ASC
        """
    )
    fun getCategories(categoryIds: Set<Int>): LiveData<List<RecipeWithStages>>

    @Query(
        """SELECT * FROM recipes,categories
WHERE id == recipe_id AND
category_Id IN (:categoryIds) AND
likedByMe == 0
GROUP BY id
ORDER BY id ASC
        """
    )
    fun getCategoriesLiked(categoryIds: List<Int>): LiveData<List<RecipeWithStages>>

    @Insert
    fun insertRecipe(recipe: RecipeEntity): Long

    @Insert
    fun insertStage(stage: StageEntity)

    @Insert
    fun insertCategory(category: CategoryIdEntity)

//    @Query(
//        """UPDATE recipes SET
//            title = :title
//            categories = :categories
//            liked = :liked
//            author = :author
//            WHERE id = :id
//                    """
//    )

//    fun updateContentById(
//        id: Long,
//        title: String,
//        categories: List<Int>,
//        liked: Boolean,
//        author: String
//    )

    @Query(
        """
        UPDATE recipes SET
        likedByMe = CASE WHEN likedByMe THEN 0 ELSE 1 END
        WHERE id = :id
        """
    )
    fun likeById(id: Long)

//    @Query("""
//        UPDATE recipes SET
//        shareCount = shareCount + 1
//        WHERE id = :id
//        """)
//    fun share(id: Long)

    @Query("DELETE FROM recipes WHERE id = :id")
    fun removeById(id: Long)
}