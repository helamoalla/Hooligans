<?php

namespace App\Repository;

use App\Entity\Bonplan;
use App\Entity\Feedback;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Bonplan>
 *
 * @method Bonplan|null find($id, $lockMode = null, $lockVersion = null)
 * @method Bonplan|null findOneBy(array $criteria, array $orderBy = null)
 * @method Bonplan[]    findAll()
 * @method Bonplan[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class BonplanRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Bonplan::class);
    }

    public function save(Bonplan $entity, bool $flush = false): void
    {
        $this->getEntityManager()->persist($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

    public function remove(Bonplan $entity, bool $flush = false): void
    {
        $this->getEntityManager()->remove($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }
    public function getAllBonPlanWithFeedbacks(){
        $entityManager=$this->getEntityManager();
        $query=$entityManager
            ->createQuery("
            SELECT b, COUNT(f.bonplan) AS count_feeds
            FROM App\Entity\Bonplan b
            LEFT JOIN App\Entity\Feedback f
            WITH b = f.bonplan
            WHERE b.etat ='accepté'
            GROUP BY b
        ")
        ;
        return $query->getResult();
    }

    public function validateBonplan($bonplan){
        $entityManager=$this->getEntityManager();
        $query=$entityManager
            ->createQuery("
            UPDATE App\Entity\Bonplan b SET b.etat = 'accepté' WHERE b.id = :bonplan 

        ")
        ->setParameter('bonplan',$bonplan)
        ;
        return $query->getResult();
    }

    public function getAllValidateBonplan(){
        $entityManager=$this->getEntityManager();
        $query=$entityManager
            ->createQuery("
            SELECT b
            FROM App\Entity\Bonplan b
            where b.etat = 'accepté'
        ")
        ;
        return $query->getResult();
    }

//    /**
//     * @return Bonplan[] Returns an array of Bonplan objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('b')
//            ->andWhere('b.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('b.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Bonplan
//    {
//        return $this->createQueryBuilder('b')
//            ->andWhere('b.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
