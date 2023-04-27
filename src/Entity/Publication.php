<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Publication
 *
 * @ORM\Table(name="publication")
 * @ORM\Entity
 */
class Publication
{
    /**
     * @var int
     *
     * @ORM\Column(name="idPost", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idpost;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=2000, nullable=false)
     * @Assert\NotBlank
     * @Assert\Length(
     *      min = 5,
     *      max = 50,
     *      minMessage = "Your message must be at least {{ limit }} characters long",
     *      maxMessage = "Your message cannot be longer than {{ limit }} characters"
     * )
     * @Assert\Regex(
     *     pattern="/^[A-Z]/",
     *     match=true,
     *     message="Votre message doit commencer par une lettre majuscule"
     * )
     * @Assert\Regex(
     *     pattern = "/[#?!@$%^&*-]+/i",
     *     match=false,
     *     message="Votre message ne doit pas contenir un caractére spéciale" 
     * )
     */
    private $description;

    /**
     * @var int
     *
     * @ORM\Column(name="nbLikes", type="integer", nullable=false)
     */
    private $nblikes = '0';

    /**
     * @var int
     *
     * @ORM\Column(name="NbDislike", type="integer", nullable=false)
     */
    private $nbdislike = '0';

    /**
     * @var int
     *
     * @ORM\Column(name="nbComments", type="integer", nullable=false)
     */
    private $nbcomments = '0';

     /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idUser", referencedColumnName="id")
     * })
     */
    private $idUser;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=true)
     * @Assert\LessThanOrEqual("today")
     */
    private $date;

    public function getIdpost(): ?int
    {
        return $this->idpost;
    }
    

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getNblikes(): ?int
    {
        return $this->nblikes;
    }

    public function setNblikes(int $nblikes): self
    {
        $this->nblikes = $nblikes;

        return $this;
    }

    public function getNbdislike(): ?int
    {
        return $this->nbdislike;
    }

    public function setNbdislike(int $nbdislike): self
    {
        $this->nbdislike = $nbdislike;

        return $this;
    }

    public function getNbcomments(): ?int
    {
        return $this->nbcomments;
    }

    public function setNbcomments(int $nbcomments): self
    {
        $this->nbcomments = $nbcomments;

        return $this;
    }

    public function getIdUser(): ?User
    {
        return $this->idUser;
    }

    public function setIdUser(User $idUser): self
    {
        $this->idUser = $idUser;

        return $this;
    }


    public function __toString()
    {
        return $this->idpost;
    }
}
