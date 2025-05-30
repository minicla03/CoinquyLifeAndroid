package minicla03.coinquylifek.APP

interface IGenericDataSource<T>
{
    suspend fun <T> fetch(request: Request<T>): Result<T>
    suspend fun <T> send(command: Command<T>): Result<T>
}